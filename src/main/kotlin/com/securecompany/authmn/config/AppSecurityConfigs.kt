package com.securecompany.authmn.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
class AppSecurityConfigs {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .authorizeExchange()
            .pathMatchers("/authenticate/**")
            .permitAll()
            .and()
            .csrf().disable()
            .build()
    }
}