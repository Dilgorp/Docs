package ru.dilgorp.docs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import ru.dilgorp.docs.model.role.authority.Authority
import ru.dilgorp.docs.model.role.authority.document.ContractAuthority
import ru.dilgorp.docs.model.role.authority.document.PackingListAuthority

@SpringBootApplication
class DemoApplication {
    @Bean
    fun fullAuthorities() : List<Authority> {
        val authorities = ArrayList<Authority>()

        ContractAuthority.values().forEach { authorities.add(it) }
        PackingListAuthority.values().forEach { authorities.add(it) }

        return authorities
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
