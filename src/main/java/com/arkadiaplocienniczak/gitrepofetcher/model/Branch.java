package com.arkadiaplocienniczak.gitrepofetcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a branch from repository on GitHub.
 * Contains details about the branch such as its name and commit.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

    private String name;
    private Commit commit;

}
