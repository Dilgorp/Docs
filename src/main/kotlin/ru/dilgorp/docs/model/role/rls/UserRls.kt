package ru.dilgorp.docs.model.role.rls

import ru.dilgorp.docs.model.user.User

interface UserRls {
    fun availableUsers(): Set<User>
}