package com.arkadiaplocienniczak.gitrepofetcher.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ClientConfig {

    @Value("${github.api.url}")
    private String gitHubApiUrl;

    @Value("${github.token}")
    private String token;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(gitHubApiUrl)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
    }
}
