package ru.dilgorp.docs.security.service

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.dilgorp.docs.model.role.Role
import ru.dilgorp.docs.repository.UserRepository

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = (userRepository.findByLogin(username)
            ?: throw UsernameNotFoundException(String.format("User with username '%s' not found", username)))

        return User(
            user.login,
            user.password,
            mapToGrantedAuthority(user.roles)
        )
    }

    private fun mapToGrantedAuthority(roles: List<Role>): MutableCollection<out GrantedAuthority> {
        val authorities = ArrayList<GrantedAuthority>()
        roles.forEach {role ->
            role.authorities.forEach{
                authorities.add(it.toGrantedAuthority())
            }
        }

        return authorities
    }
}