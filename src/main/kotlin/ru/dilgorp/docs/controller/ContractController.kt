package ru.dilgorp.docs.controller

import org.springframework.web.bind.annotation.*
import ru.dilgorp.docs.EndPointsScheme
import ru.dilgorp.docs.dao.ContractDao
import ru.dilgorp.docs.model.document.contract.Contract
import ru.dilgorp.docs.repository.UserRepository
import java.security.Principal

@RestController()
@RequestMapping(EndPointsScheme.Document.Contract.root)
class ContractController(
    private val userRepository: UserRepository,
    private val contractDao: ContractDao
) {

    @GetMapping
    fun getContracts(principal: Principal): List<Contract>{
        val user = userRepository.findByLogin(principal.name) ?: return emptyList()
        return contractDao.findAll(user.roles)
    }

    @PostMapping
    fun postContract(): String{
        return "create contract"
    }

    @PutMapping
    fun putContract(): String{
        return "edit contract"
    }

    @DeleteMapping
    fun deleteContract(): String{
        return "delete contract"
    }
}