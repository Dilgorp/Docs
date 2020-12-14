package ru.dilgorp.docs.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class SecurityConfig {
    @Value("\${security.header-string}")
    private lateinit var headerString: String

    @Value("\${security.secret}")
    private lateinit var secret: String

    @Value("\${security.token-prefix}")
    private lateinit var tokenPrefix: String

    @Bean
    fun headerString() = headerString

    @Bean
    fun secret() = secret

    @Bean
    fun tokenPrefix() = tokenPrefix

    @Bean
    fun bcryptEncoder() = BCryptPasswordEncoder()
}