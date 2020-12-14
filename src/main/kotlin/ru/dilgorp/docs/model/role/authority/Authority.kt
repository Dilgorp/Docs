package ru.dilgorp.docs.model.role.authority

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

interface Authority {
    val subject: String
    val authName: String

    fun toGrantedAuthority(): GrantedAuthority{
        return SimpleGrantedAuthority(authName)
    }
}