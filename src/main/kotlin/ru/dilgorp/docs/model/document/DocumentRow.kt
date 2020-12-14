package ru.dilgorp.docs.model.document

import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class DocumentRow<D : DocumentCard>(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "dcr_id")
    open val dcr_id: Long = 0,

    @ManyToOne
    @Type(type = "bigint")
    @JoinColumn(name = "owner_dc_id", referencedColumnName = "dc_id")
    open val documentCard: D? = null,

    @Column(name = "number")
    open val number: Int = 0,

    @Column(name = "amount")
    open val amount: Int = 0,

    @Column(name = "price")
    open val price: Int = 0,

    @Column(name = "sum")
    open val sum: Int = 0
)