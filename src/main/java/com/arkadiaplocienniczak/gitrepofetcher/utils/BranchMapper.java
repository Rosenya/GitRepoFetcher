package com.arkadiaplocienniczak.gitrepofetcher.utils;

import com.arkadiaplocienniczak.gitrepofetcher.model.Branch;
import com.arkadiaplocienniczak.gitrepofetcher.model.BranchDTO;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper {

    public BranchDTO branchToDTO(Branch branch) {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setName(branch.getName());
        branchDTO.setCommit(branch.getCommit());
        return branchDTO;
    }

    public Branch branchDTOToBranch(BranchDTO branchDTO) {
        Branch branch = new Branch();
        branch.setName(branchDTO.getName());
        branch.setCommit(branchDTO.getCommit());
        return branch;
    }

}
