package com.arkadiaplocienniczak.gitrepofetcher.config;

import org.springframework.beans.factory.annotation.Value;

public class ClientConfig {

    @Value("${github.api.url}")
    private String gitHubApiUrl;

    @Value("${github.token}")
    private String token;


}
