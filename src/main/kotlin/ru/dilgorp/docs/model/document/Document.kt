package ru.dilgorp.docs.model.document

import ru.dilgorp.docs.model.Contractor
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class DocumentCard(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "dc_id")
    open val dc_id: Long = 0,

    @Column(name = "number")
    open val number: String = "",

    @Column(name = "date")
    open val date: LocalDateTime = LocalDateTime.MIN,

    @ManyToOne
    @JoinColumn(name = "contractor_id", referencedColumnName = "contractor_id")
    open val contractor: Contractor? = null
)
