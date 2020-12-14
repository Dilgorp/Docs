package ru.dilgorp.docs.security.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import ru.dilgorp.docs.model.user.User
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    private val authManager: AuthenticationManager,
    private val headerString: String,
    private val secret: String,
    private val tokenPrefix: String,
    private val objectMapper: ObjectMapper
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val user = objectMapper.readValue(request.inputStream, User::class.java)
        return authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                user.login,
                user.password
            )
        )
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val token = JWT.create()
            .withSubject((authResult!!.principal as org.springframework.security.core.userdetails.User).username)
            .sign(Algorithm.HMAC512(secret.toByteArray()))
        response!!.addHeader(headerString, tokenPrefix + token)
    }
}