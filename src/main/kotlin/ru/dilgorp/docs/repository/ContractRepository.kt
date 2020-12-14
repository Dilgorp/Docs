package ru.dilgorp.docs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.dilgorp.docs.model.Contractor
import ru.dilgorp.docs.model.document.contract.Contract

@Repository
interface ContractRepository : JpaRepository<Contract, Long>{
    fun findByContractorIn(contractors: Set<Contractor>): List<Contract>
}