package ru.dilgorp.docs.model.document.packinglist

import ru.dilgorp.docs.model.document.DocumentRow
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "t_packing_list_row")
class PackingListRow(
    @Column(name = "product")
    val product: String = ""
) : DocumentRow<PackingList>()