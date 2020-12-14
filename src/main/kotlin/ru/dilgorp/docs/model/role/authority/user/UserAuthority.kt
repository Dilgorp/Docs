package ru.dilgorp.docs.model.role.authority.user

import ru.dilgorp.docs.model.role.authority.Authority

enum class UserAuthority : Authority {
    CREATE, EDIT, READ, DELETE;

    override val authName: String
        get() = "${subject}_${name}"

    override val subject: String
        get() = "USER"
}