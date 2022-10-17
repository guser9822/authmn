package com.securecompany.authmn

import com.securecompany.authmn.config.app.AppConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AppConfig::class)
class AuthmnApplication

fun main(args: Array<String>) {
    runApplication<AuthmnApplication>(*args)
}
