package ru.dilgorp.docs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.dilgorp.docs.model.user.User

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByLogin(login: String?): User?
}