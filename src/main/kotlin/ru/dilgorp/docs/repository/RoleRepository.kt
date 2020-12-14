package ru.dilgorp.docs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.dilgorp.docs.model.role.Administrator
import ru.dilgorp.docs.model.role.Economist

@Repository
interface EconomistRoleRepository: JpaRepository<Economist, Long>

@Repository
interface AdministratorRoleRepository: JpaRepository<Administrator, Long>