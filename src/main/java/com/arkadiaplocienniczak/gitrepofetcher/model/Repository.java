package com.arkadiaplocienniczak.gitrepofetcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a user repository on GitHub.
 * Contains details about the repository such as its login, whether it's a fork, and branches.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repository {

    private String repositoryName;
    private boolean isForked;
    private Owner ownerLogin;
    private List<Branch> branches;

}
