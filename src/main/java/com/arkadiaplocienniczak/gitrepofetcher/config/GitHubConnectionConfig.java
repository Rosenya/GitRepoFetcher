package com.arkadiaplocienniczak.gitrepofetcher.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GitHubConnectionConfig {

    @Value("${github.api.url}")
    private String gitHubApiUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(gitHubApiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
