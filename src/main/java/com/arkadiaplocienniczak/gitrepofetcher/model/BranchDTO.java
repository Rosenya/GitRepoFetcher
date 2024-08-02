package com.arkadiaplocienniczak.gitrepofetcher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a GitHub branch.
 * <p>
 * This class is used to map branch information from JSON responses from GitHub API.
 * It contains information about the branch name and the associated commit.
 * </p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BranchDTO {

    /**
     * The name of the branch.
     * <p>
     * This field is mapped from the "name" property in the JSON response.
     * </p>
     */
    @JsonProperty("name")
    private String name;

    /**
     * The commit associated with this branch.
     * <p>
     * This field is mapped from the "commit" property in the JSON response.
     * </p>
     */
    @JsonProperty("commit")
    private Commit commit;

}
