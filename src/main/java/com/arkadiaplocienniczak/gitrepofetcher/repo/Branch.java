package com.arkadiaplocienniczak.gitrepofetcher.repo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Branch {

    private String name;
    private String lastCommitSHA;

}
