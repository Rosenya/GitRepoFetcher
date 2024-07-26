package com.arkadiaplocienniczak.gitrepofetcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repository {

    private String repositoryName;
    private boolean isForked;
    private Owner ownerLogin;
    private List<Branch> branches;

}
