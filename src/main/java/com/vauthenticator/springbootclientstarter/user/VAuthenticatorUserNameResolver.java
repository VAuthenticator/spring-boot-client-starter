package com.vauthenticator.springbootclientstarter.user;

import org.springframework.security.core.Authentication;

import java.security.Principal;

public class VAuthenticatorUserNameResolver {

    private final String userNameSource;

    public VAuthenticatorUserNameResolver(String userNameSource) {
        this.userNameSource = userNameSource;
    }

    public String getUserNameFor(Authentication authentication) {
        return authentication.getName();
    }

    public String getUserNameFor(Principal principal) {
        return principal.getName();
    }

}
