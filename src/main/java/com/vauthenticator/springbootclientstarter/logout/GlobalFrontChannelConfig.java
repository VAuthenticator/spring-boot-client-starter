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
    public GlobalFrontChannelLogoutProvider globalFrontChannelLogoutProviderWithoutDiscovery(@Value("${postLogoutRedirectUri:''}") String postLogoutRedirectUri,
                                                                                             @Value("${oidcEndSessionUrl}") String oidcEndSessionUrl) {
        return new GlobalFrontChannelLogoutProvider(postLogoutRedirectUri,
                null,
                oidcEndSessionUrl,
                null);
    }

}
