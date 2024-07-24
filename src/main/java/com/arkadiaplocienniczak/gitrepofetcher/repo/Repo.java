package com.arkadiaplocienniczak.gitrepofetcher.repo;

import com.arkadiaplocienniczak.gitrepofetcher.branch.Branch;
import com.arkadiaplocienniczak.gitrepofetcher.owner.Owner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repo {

    @JsonProperty("name")
    private String repositoryName;
    @JsonProperty("fork")
    private boolean isForked;
    @JsonProperty("owner")
    private Owner ownerLogin;
    private List<Branch> branches;
}
