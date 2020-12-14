package ru.dilgorp.docs.controller

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.dilgorp.docs.model.Contractor
import ru.dilgorp.docs.model.document.contract.Contract
import ru.dilgorp.docs.model.role.Administrator
import ru.dilgorp.docs.model.role.Economist
import ru.dilgorp.docs.model.user.User
import ru.dilgorp.docs.repository.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/generate")
class GeneratorController(
    private val userRepository: UserRepository,
    private val contractorRepository: ContractorRepository,
    private val contractRepository: ContractRepository,
    private val encoder: BCryptPasswordEncoder,
    private val economistRoleRepository: EconomistRoleRepository,
    private val administratorRoleRepository: AdministratorRoleRepository
) {
    @GetMapping
    fun generateData(): String {

//        return "Data generated"

        val contractors = ArrayList<Contractor>()
        for (i in 0..3) {
            contractors.add(Contractor(name = "Contractor$i"))
        }
        contractorRepository.saveAll(contractors)

        val economist = User(
            login = "economist",
            password = encoder.encode("123456")
        )

        userRepository.save(economist)

        economistRoleRepository.save(
            Economist(
                setOf(contractors[0]),
                setOf(economist),
                economist
            )
        )

        val admin = User(
            login = "admin",
            password = encoder.encode("123456")
        )
        userRepository.save(admin)

        administratorRoleRepository.save(Administrator(admin))

        val contracts = ArrayList<Contract>()
        for (i in 0..3) {
            contracts.add(
                Contract(
                    subject = "Subject$i",
                    number = "$i",
                    contractor = contractors[i],
                    date = LocalDateTime.now()
                )
            )
        }
        contractRepository.saveAll(contracts)

        return "Data generated"
    }
}