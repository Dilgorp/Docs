package ru.dilgorp.docs.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import ru.dilgorp.docs.manager.access.AccessManager
import ru.dilgorp.docs.security.filter.AuthenticationFilter
import ru.dilgorp.docs.security.filter.AuthorizationFilter

@EnableWebSecurity
class WebSecurityConfig(
    private val accessManagers: List<AccessManager>,
    private val headerString: String,
    private val secret: String,
    private val tokenPrefix: String,
    @Qualifier("userDetailsServiceImpl")
    private val userDetailsService: UserDetailsService,
    private val encoder: BCryptPasswordEncoder,
    private val objectMapper: ObjectMapper
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http ?: return

        val authorizeRequests = http.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.GET, "/generate").permitAll()

        accessManagers.forEach { it.configureAccess(authorizeRequests) }

        authorizeRequests.and()
            .addFilter(getAuthenticationFilter())
            .addFilter(getAuthorizationFilter())
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userDetailsService).passwordEncoder(encoder)
    }

    private fun getAuthenticationFilter(): AuthenticationFilter {
        return AuthenticationFilter(
            authenticationManager(),
            headerString, secret, tokenPrefix, objectMapper
        )
    }

    private fun getAuthorizationFilter(): AuthorizationFilter {
        return AuthorizationFilter(
            authenticationManager(),
            headerString,
            secret,
            tokenPrefix,
            userDetailsService
        )
    }
}