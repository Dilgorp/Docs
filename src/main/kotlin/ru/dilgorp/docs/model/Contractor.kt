package ru.dilgorp.docs.model

import javax.persistence.*

@Entity
@Table(name = "t_contractor")
data class Contractor (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "contractor_id")
    val contractorId:  Long = 0,

    @Column(name = "name")
    val name: String = ""
)