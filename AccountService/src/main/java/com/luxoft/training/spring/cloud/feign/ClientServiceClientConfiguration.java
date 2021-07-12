package com.luxoft.training.spring.cloud.feign;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

@SuppressWarnings("deprecation")
public class ClientServiceClientConfiguration {

    @Value("${security.oauth2.client.accessTokenUri}")
    private String accessTokenUri;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new OAuth2FeignRequestInterceptor(
                new DefaultOAuth2ClientContext(),
                resource());
    }

    private OAuth2ProtectedResourceDetails resource() {
        ResourceOwnerPasswordResourceDetails resource =
                new ResourceOwnerPasswordResourceDetails();
        resource.setAccessTokenUri(accessTokenUri);
        resource.setClientId("client");
        resource.setClientSecret("secret");
        resource.setUsername("client");
        resource.setPassword("client");
        return resource;
    }
}
