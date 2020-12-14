package ru.dilgorp.docs.manager.access.user

import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer
import org.springframework.stereotype.Component
import ru.dilgorp.docs.EndPointsScheme
import ru.dilgorp.docs.manager.access.AccessManager
import ru.dilgorp.docs.model.role.authority.user.UserAuthority

@Component
class UserAccessManager : AccessManager {
    override fun configureAccess(
        configurer: ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
    ) {
        configurer
            .antMatchers(HttpMethod.GET, EndPointsScheme.User.access)
            .hasAuthority(UserAuthority.READ.authName)

            .antMatchers(HttpMethod.POST, EndPointsScheme.User.access)
            .hasAuthority(UserAuthority.CREATE.authName)

            .antMatchers(HttpMethod.PUT, EndPointsScheme.User.access)
            .hasAuthority(UserAuthority.EDIT.authName)

            .antMatchers(HttpMethod.DELETE, EndPointsScheme.User.access)
            .hasAuthority(UserAuthority.DELETE.authName)
    }
}