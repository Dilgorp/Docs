package ru.dilgorp.docs.model.role


import ru.dilgorp.docs.model.Contractor
import ru.dilgorp.docs.model.role.authority.Authority
import ru.dilgorp.docs.model.role.authority.document.ContractAuthority
import ru.dilgorp.docs.model.role.authority.document.PackingListAuthority
import ru.dilgorp.docs.model.role.authority.user.UserAuthority
import ru.dilgorp.docs.model.role.rls.ContractorRls
import ru.dilgorp.docs.model.role.rls.UserRls
import ru.dilgorp.docs.model.user.User
import javax.persistence.Entity
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "t_role_economist")
class Economist(
    @ManyToMany
    @JoinTable(name = "t_role_economist_contractor")
    private val availableContractors: Set<Contractor> = emptySet(),

    @ManyToMany
    @JoinTable(name = "t_role_economist_users")
    private val availableUsers: Set<User> = emptySet(),
    user: User? = null
) : Role(user = user), ContractorRls, UserRls {

    override val name: String
        get() = "ECONOMIST"


    override val authorities: List<Authority>
        get() = listOf(
            ContractAuthority.READ,
            ContractAuthority.EDIT,
            ContractAuthority.CREATE,

            PackingListAuthority.READ,
            PackingListAuthority.EDIT,

            UserAuthority.READ
        )

    override fun availableContractors(): Set<Contractor> {
        return availableContractors
    }

    override fun availableUsers(): Set<User> {
        return availableUsers
    }
}