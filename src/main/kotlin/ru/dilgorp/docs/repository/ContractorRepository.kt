package ru.dilgorp.docs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.dilgorp.docs.model.Contractor

@Repository
interface ContractorRepository : JpaRepository<Contractor, Long>