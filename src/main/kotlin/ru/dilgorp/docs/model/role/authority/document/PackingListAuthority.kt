package ru.dilgorp.docs.model.role.authority.document

import ru.dilgorp.docs.model.role.authority.Authority

enum class PackingListAuthority : Authority {
    CREATE, EDIT, READ, DELETE;

    override val authName: String
        get() = "${subject}_${name}"

    override val subject: String
        get() = "PACKING_LIST"
}