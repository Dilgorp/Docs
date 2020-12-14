package ru.dilgorp.docs.model.role

import ru.dilgorp.docs.model.role.authority.Authority
import ru.dilgorp.docs.model.role.authority.document.ContractAuthority
import ru.dilgorp.docs.model.role.authority.document.PackingListAuthority
import ru.dilgorp.docs.model.user.User
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "t_role_administrator")
class Administrator(
    user: User? = null
) : Role(user = user) {

    override val name: String
        get() = "ADMIN"

    override val authorities: List<Authority>
        get() = fullAuthorities()

    private fun fullAuthorities(): List<Authority> {
        val authorities = ArrayList<Authority>()

        ContractAuthority.values().forEach { authorities.add(it) }
        PackingListAuthority.values().forEach { authorities.add(it) }

        return authorities
    }
}