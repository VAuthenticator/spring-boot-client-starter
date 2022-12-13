# Spring Boot Client Starter

This starter is all teh needed to start with vauthetnciator.
It provides abstractions to have:
- global single front channel logout
- access token auto refresh
- needed abstractions to have session management   

# minimum requirements
Since that it is based on Spring Boot 3.x and Spring 6.x the minimum jdk required is the jdk 17


# Configuration sample

Below there is a sample configuration inspired from VAuthenticator Management application

```kotlin

import ....
.....

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
class WebSecurityConfig(
    private val vAuthenticatorOidcUserService: VAuthenticatorOidcUserService,
    private val oAuth2AuthorizationRequestResolverWithSessionState: OAuth2AuthorizationRequestResolverWithSessionState
) {

    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable().headers().frameOptions().disable()

        http.logout()
            .deleteCookies("opbs")
            .invalidateHttpSession(true)
            .logoutSuccessUrl("/secure/admin/index")


        http.oauth2Login().defaultSuccessUrl("......")
            .userInfoEndpoint()
            .oidcUserService(vAuthenticatorOidcUserService)
            .and()
            .authorizationEndpoint()
            .authorizationRequestResolver(oAuth2AuthorizationRequestResolverWithSessionState);

        http.authorizeHttpRequests { authz ->
            authz.requestMatchers("/actuator/**", "/oidc_logout.html").permitAll()
                .anyRequest().hasAnyAuthority(adminRole)
        }

        return http.build()
    }

    @Bean
    fun vauthenticatorRestTemplate(oAuth2TokenResolver: OAuth2TokenResolver) =
        RestTemplateBuilder()
            .additionalInterceptors(BearerTokenInterceptor(oAuth2TokenResolver))
            .build()

}

```