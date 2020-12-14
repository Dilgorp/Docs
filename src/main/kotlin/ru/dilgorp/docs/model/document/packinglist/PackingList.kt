package ru.dilgorp.docs.model.document.packinglist

import ru.dilgorp.docs.model.Contractor
import ru.dilgorp.docs.model.document.DocumentCard
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "t_packing_list")
class PackingList(
    @OneToOne
    @JoinColumn(name = "root_id", referencedColumnName = "dc_id")
    val root: DocumentCard? = null,

    @OneToMany(mappedBy = "documentCard")
    val rows: List<PackingListRow> = emptyList(),

    dc_id: Long = 0,
    number: String = "",
    date: LocalDateTime = LocalDateTime.MIN,
    contractor: Contractor? = null
) : DocumentCard(dc_id, number, date, contractor)