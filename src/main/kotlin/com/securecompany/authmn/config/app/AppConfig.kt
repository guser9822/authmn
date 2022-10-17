package com.securecompany.authmn.config.app

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "app")
@ConstructorBinding
data class AppConfig(val host: String, val oauth: Oauth) {
    data class Oauth(
        val clientId: String,
        val secret: String,
        val scopes: List<String>,
        val redirectEndpoint: String,
        val tokenServerEncodedUrl: String,
        val googleApiHost: String,
        val idTokenApi: String,
    )
}