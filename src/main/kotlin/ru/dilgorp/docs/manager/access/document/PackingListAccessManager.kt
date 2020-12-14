package ru.dilgorp.docs.manager.access.document

import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer
import org.springframework.stereotype.Component
import ru.dilgorp.docs.EndPointsScheme
import ru.dilgorp.docs.manager.access.AccessManager
import ru.dilgorp.docs.model.role.authority.document.PackingListAuthority

@Component
class PackingListAccessManager : AccessManager {

    override fun configureAccess(
        configurer: ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
    ) {
        configurer
            .antMatchers(HttpMethod.GET, EndPointsScheme.Document.PackingList.access)
                .hasAuthority(PackingListAuthority.READ.authName)

            .antMatchers(HttpMethod.POST, EndPointsScheme.Document.PackingList.access)
                .hasAuthority(PackingListAuthority.CREATE.authName)

            .antMatchers(HttpMethod.PUT, EndPointsScheme.Document.PackingList.access)
                .hasAuthority(PackingListAuthority.EDIT.authName)

            .antMatchers(HttpMethod.DELETE, EndPointsScheme.Document.PackingList.access)
                .hasAuthority(PackingListAuthority.DELETE.authName)
    }
}