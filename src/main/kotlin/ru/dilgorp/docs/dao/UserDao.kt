package ru.dilgorp.docs.dao

import org.springframework.stereotype.Service
import ru.dilgorp.docs.model.role.Role
import ru.dilgorp.docs.model.role.rls.UserRls
import ru.dilgorp.docs.model.user.User
import ru.dilgorp.docs.repository.UserRepository

@Service
class UserDao(
    private val userRepository: UserRepository
) {
    fun findAll(roles: List<Role>): List<User> {
        val users = HashSet<User>()
        roles.forEach { role ->
            if (role is UserRls) {
                users.addAll(role.availableUsers())
            }
        }

        return if (users.isEmpty())
            userRepository.findAll()
        else
            users.toList()
    }
}