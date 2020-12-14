package ru.dilgorp.docs.dao

import org.springframework.stereotype.Service
import ru.dilgorp.docs.model.Contractor
import ru.dilgorp.docs.model.document.contract.Contract
import ru.dilgorp.docs.model.role.Role
import ru.dilgorp.docs.model.role.rls.ContractorRls
import ru.dilgorp.docs.repository.ContractRepository

@Service
class ContractDao(
    private val contractRepository: ContractRepository
) {
    fun findAll(roles: List<Role>): List<Contract> {
        val contractors = HashSet<Contractor>()
        roles.forEach { role ->
            if (role is ContractorRls) {
                contractors.addAll(role.availableContractors())
            }
        }
        return if (contractors.isEmpty())
            contractRepository.findAll()
        else
            contractRepository.findByContractorIn(contractors)
    }
}