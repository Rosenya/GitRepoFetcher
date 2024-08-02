package com.arkadiaplocienniczak.gitrepofetcher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing a GitHub repository.
 * <p>
 * This class is used to map repository information from JSON responses from GitHub API.
 * It includes details about the repository's name, whether it is a fork, the repository's owner,
 * and the branches associated with the repository.
 * </p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryDTO {

    /**
     * The name of the repository.
     * <p>
     * This field is mapped from the "name" property in the JSON response.
     * </p>
     */
    @JsonProperty("name")
    private String repositoryName;

    /**
     * Indicates whether the repository is a fork.
     * <p>
     * This field is mapped from the "fork" property in the JSON response.
     * </p>
     */
    @JsonProperty("fork")
    private boolean isForked;

    /**
     * The owner of the repository.
     * <p>
     * This field is mapped from the "owner" property in the JSON response.
     * It is represented by an instance of the {@link Owner} class.
     * </p>
     */
    @JsonProperty("owner")
    private Owner ownerLogin;

    /**
     * The list of branches associated with the repository.
     * <p>
     * This field is not mapped from any JSON property directly, but is populated separately.
     * It contains a list of {@link BranchDTO} objects representing the branches in the repository.
     * </p>
     */
    private List<BranchDTO> branches;

}
