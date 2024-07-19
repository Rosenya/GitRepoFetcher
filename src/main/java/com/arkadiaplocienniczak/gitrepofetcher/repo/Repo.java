package com.arkadiaplocienniczak.gitrepofetcher.repo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Repo {

    private String name;
    private String ownerName;
    private List<Branch> branches;
    boolean isForked;

}
