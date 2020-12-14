package ru.dilgorp.docs.security.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private val headerString: String,
    private val secret: String,
    private val tokenPrefix: String,
    private val userDetailsService: UserDetailsService
): BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader(headerString)
        if(header == null || !header.startsWith(tokenPrefix)){
            chain.doFilter(request, response)
            return
        }

        SecurityContextHolder.getContext().authentication = getAuthenticationToken(request)
        chain.doFilter(request, response)
    }

    private fun getAuthenticationToken(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(headerString) ?: return null
        val user = JWT.require(Algorithm.HMAC512(secret.toByteArray()))
            .build()
            .verify(token.replace(tokenPrefix, ""))
            .subject ?: return null

        val userDetails = userDetailsService.loadUserByUsername(user)

        return UsernamePasswordAuthenticationToken(
            userDetails.username, userDetails.password, userDetails.authorities
        )
    }
}