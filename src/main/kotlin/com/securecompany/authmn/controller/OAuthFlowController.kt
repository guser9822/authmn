package com.securecompany.authmn.controller

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.securecompany.authmn.config.app.AppConfig
import com.securecompany.authmn.model.UserId
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/authenticate")
class OAuthFlowController(val appConfig: AppConfig, val appWebClient: WebClient) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    val googleAuthFlow: GoogleAuthorizationCodeFlow = GoogleAuthorizationCodeFlow.Builder(
        NetHttpTransport.Builder().build(),
        GsonFactory.getDefaultInstance(),
        appConfig.oauth.clientId,
        appConfig.oauth.secret,
        appConfig.oauth.scopes
    )
        .setAccessType("online") // Web application
        .setApprovalPrompt("auto")// Web application
        .build()

    @GetMapping("/initializeFlow")
    fun initializeFlow(): Mono<String> {
        return Mono.just(
            googleAuthFlow.newAuthorizationUrl()
                .setRedirectUri(appConfig.host + appConfig.oauth.redirectEndpoint)
                .toURL()
                .toString()
        )
    }

    @GetMapping("/authcode")
    fun authenticatePost(@RequestParam code: String): Mono<String> {
        val tokenRequestResp = GoogleAuthorizationCodeTokenRequest(
            NetHttpTransport.Builder().build(),
            GsonFactory.getDefaultInstance(),
            appConfig.oauth.tokenServerEncodedUrl,
            appConfig.oauth.clientId,
            appConfig.oauth.secret,
            code,
            appConfig.host + appConfig.oauth.redirectEndpoint
        ).execute()
        return appWebClient
            .get()
            .uri {
                val builtUri = it
                    .path(appConfig.oauth.idTokenApi)
                    .queryParam("id_token", tokenRequestResp.idToken)
                    .build()
                log.debug("URL: $builtUri")
                builtUri
            }
            .retrieve()
            .bodyToMono(UserId::class.java)
            .map {
                it?.email ?: ""
            }
    }

}