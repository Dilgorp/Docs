package ru.dilgorp.docs.model.role.authority.document

import ru.dilgorp.docs.model.role.authority.Authority

enum class ContractAuthority : Authority {
    CREATE, EDIT, READ, DELETE;

    override val authName: String
        get() = "${subject}_${name}"

    override val subject: String
        get() = "CONTRACT"
}