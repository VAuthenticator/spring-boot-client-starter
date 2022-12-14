package com.vauthenticator.springbootclientstarter.logout;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@AutoConfiguration
@ComponentScan("com.vauthenticator.springbootclientstarter.logout")
public class GlobalFrontChannelConfig {

    @Bean("globalFrontChannelLogoutProvider")
    @ConditionalOnProperty(value = {"postLogoutRedirectUri", "auth.oidcIss","preferDiscovery"})
    public GlobalFrontChannelLogoutProvider globalFrontChannelLogoutProviderWithDiscovery(@Value("${postLogoutRedirectUri}") String postLogoutRedirectUri,
                                                                             @Value("${auth.oidcIss}") String oidConnectDiscoveryEndPoint) {
        return new GlobalFrontChannelLogoutProvider(postLogoutRedirectUri,
                oidConnectDiscoveryEndPoint + "/.well-known/openid-configuration",
                null,
                new RestTemplate());
    }

    @Bean("globalFrontChannelLogoutProvider")
    @ConditionalOnProperty(value = {"endSessionWithoutDiscovery"})
    public GlobalFrontChannelLogoutProvider globalFrontChannelLogoutProviderWithoutDiscovery(@Value("${postLogoutRedirectUri:''}") String postLogoutRedirectUri,
                                                                                             @Value("${oidcEndSessionUrl}") String oidcEndSessionUrl) {
        return new GlobalFrontChannelLogoutProvider(postLogoutRedirectUri,
                null,
                oidcEndSessionUrl,
                null);
    }

}
