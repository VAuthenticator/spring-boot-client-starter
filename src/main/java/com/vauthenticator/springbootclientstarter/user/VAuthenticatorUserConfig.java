package com.vauthenticator.springbootclientstarter.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;

@AutoConfiguration
public class VAuthenticatorUserConfig {

    @Bean
    public VAuthenticatorOidcUserService vAuthenticatorOidcUserService(@Value("${vauthenticator.authorities-claim-name:authorities}") String authorize){
        OidcUserService oidcUserService = new OidcUserService();
        return new VAuthenticatorOidcUserService(oidcUserService, authorize);
    }

    @Bean
    public VAuthenticatorUserNameResolver vAuthenticatorUserNameResolver() {
        return new VAuthenticatorUserNameResolver("username");
    }
}
