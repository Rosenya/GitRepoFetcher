package com.arkadiaplocienniczak.gitrepofetcher.utils;

import com.arkadiaplocienniczak.gitrepofetcher.model.Branch;
import com.arkadiaplocienniczak.gitrepofetcher.model.BranchDTO;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting {@link BranchDTO} instances to {@link Branch} instances.
 * <p>
 * This class provides the functionality to map a {@link BranchDTO}, which represents a branch's
 * data transfer object, to a {@link Branch} domain model object. It is used to facilitate the
 * transformation between different layers of the application, such as between the API layer and
 * the service layer.
 * </p>
 */
@Component
public class BranchMapper {

    /**
     * Converts a {@link BranchDTO} to a {@link Branch}.
     * <p>
     * This method takes a {@link BranchDTO} object and maps its fields to a new {@link Branch}
     * object. The conversion includes copying the branch name and commit details from the DTO
     * to the domain model.
     * </p>
     *
     * @param branchDTO The {@link BranchDTO} object to be converted.
     * @return A {@link Branch} object with fields populated from the {@link BranchDTO}.
     */
    public Branch branchDTOToBranch(BranchDTO branchDTO) {
        Branch branch = new Branch();
        branch.setName(branchDTO.getName());
        branch.setCommit(branchDTO.getCommit());
        return branch;
    }

}
