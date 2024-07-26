package com.arkadiaplocienniczak.gitrepofetcher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryDTO {

    @JsonProperty("name")
    private String repositoryName;
    @JsonProperty("fork")
    private boolean isForked;
    @JsonProperty("owner")
    private Owner ownerLogin;
    private List<BranchDTO> branches;

}
