package ru.dilgorp.docs.model.role

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import ru.dilgorp.docs.model.role.authority.Authority
import ru.dilgorp.docs.model.user.User
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    open val roleId: Long = 0,
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    open val user: User? = null
) {

    abstract val name: String
    abstract val authorities: List<Authority>

    fun toGrantedAuthority(): GrantedAuthority{
        return SimpleGrantedAuthority("ROLE_${name}")
    }
}