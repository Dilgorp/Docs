package ru.dilgorp.docs.model.user

import ru.dilgorp.docs.model.role.Role
import javax.persistence.*

@Entity
@Table(name = "t_user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    val userId: Long = 0,

    @Column(name = "login")
    val login: String = "",

    @Column(name = "password", length = 60)
    val password: String = "",

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    val roles: MutableList<Role> = mutableListOf()
)