package com.securecompany.authmn.client

import com.securecompany.authmn.config.app.AppConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun appWebClient(appConfig: AppConfig): WebClient {
        return WebClient
            .builder()
            .baseUrl(appConfig.oauth.googleApiHost)
            .build()
    }
}