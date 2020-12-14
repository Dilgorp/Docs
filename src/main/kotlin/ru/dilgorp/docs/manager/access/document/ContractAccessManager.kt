package ru.dilgorp.docs.manager.access.document

import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer
import org.springframework.stereotype.Component
import ru.dilgorp.docs.EndPointsScheme
import ru.dilgorp.docs.manager.access.AccessManager
import ru.dilgorp.docs.model.role.authority.document.ContractAuthority

@Component
class ContractAccessManager : AccessManager {

    override fun configureAccess(
        configurer: ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
    ) {
        configurer
            .antMatchers(HttpMethod.GET, EndPointsScheme.Document.Contract.access)
                .hasAuthority(ContractAuthority.READ.authName)

            .antMatchers(HttpMethod.POST, EndPointsScheme.Document.Contract.access)
                .hasAuthority(ContractAuthority.CREATE.authName)

            .antMatchers(HttpMethod.PUT, EndPointsScheme.Document.Contract.access)
                .hasAuthority(ContractAuthority.EDIT.authName)

            .antMatchers(HttpMethod.DELETE, EndPointsScheme.Document.Contract.access)
                .hasAuthority(ContractAuthority.DELETE.authName)
    }
}
