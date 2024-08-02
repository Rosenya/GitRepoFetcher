package com.arkadiaplocienniczak.gitrepofetcher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents a commit from branc on GitHub.
 * Contains details about the commit such as its sha.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {

    @JsonProperty("sha")
    private String sha;

}
