package ru.dilgorp.docs.model.role.rls

import ru.dilgorp.docs.model.Contractor

interface ContractorRls {
    fun availableContractors(): Set<Contractor>
}