package com.arkadiaplocienniczak.gitrepofetcher.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for setting up a {@link WebClient} to interact with the GitHub API.
 * <p>
 * This class provides the configuration required to create a {@link WebClient} instance
 * with a base URL pointing to the GitHub API and default headers for JSON content type.
 * The {@link WebClient} is used for making HTTP requests to the GitHub API.
 * </p>
 */
@Component
public class GitHubConnectionConfig {

    /**
     * The base URL for the GitHub API, injected from application properties.
     */
    @Value("${github.api.url}")
    private String gitHubApiUrl;

    /**
     * Creates and configures a {@link WebClient} bean with the base URL and default headers.
     * <p>
     * The {@link WebClient} is configured with the base URL obtained from the {@code gitHubApiUrl}
     * property and sets the default content type header to {@code application/json}. This configuration
     * allows for convenient and consistent interaction with the GitHub API throughout the application.
     * </p>
     *
     * @return A configured {@link WebClient} instance.
     */    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(gitHubApiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
