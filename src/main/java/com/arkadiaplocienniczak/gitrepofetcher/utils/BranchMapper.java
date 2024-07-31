package com.arkadiaplocienniczak.gitrepofetcher.utils;

import com.arkadiaplocienniczak.gitrepofetcher.model.Branch;
import com.arkadiaplocienniczak.gitrepofetcher.model.BranchDTO;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper {

    public Branch branchDTOToBranch(BranchDTO branchDTO) {
        Branch branch = new Branch();
        branch.setName(branchDTO.getName());
        branch.setCommit(branchDTO.getCommit());
        return branch;
    }

}
