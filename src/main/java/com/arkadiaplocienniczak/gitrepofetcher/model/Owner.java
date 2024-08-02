package com.arkadiaplocienniczak.gitrepofetcher.model;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a user on GitHub.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {

    @JsonProperty("login")
    private String login;

}
