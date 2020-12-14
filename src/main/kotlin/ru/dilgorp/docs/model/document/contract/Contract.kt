package ru.dilgorp.docs.model.document.contract

import ru.dilgorp.docs.model.Contractor
import ru.dilgorp.docs.model.document.DocumentCard
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "t_contract")
class Contract(
    @Column(name = "subject")
    val subject: String = "",

    dc_id: Long = 0,
    number:String = "",
    date: LocalDateTime = LocalDateTime.MIN,
    contractor: Contractor? = null
) : DocumentCard(dc_id, number, date, contractor)