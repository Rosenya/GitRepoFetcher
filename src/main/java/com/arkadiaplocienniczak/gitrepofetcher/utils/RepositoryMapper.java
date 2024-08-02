package com.arkadiaplocienniczak.gitrepofetcher.utils;

import com.arkadiaplocienniczak.gitrepofetcher.model.Repository;
import com.arkadiaplocienniczak.gitrepofetcher.model.RepositoryDTO;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting {@link Repository} instances to {@link RepositoryDTO} instances.
 * <p>
 * This class provides functionality to map a {@link Repository} domain model object to a
 * {@link RepositoryDTO} data transfer object. This transformation is useful for separating
 * the domain model from the data representation used in API responses or other external communications.
 * </p>
 */
@Component
public class RepositoryMapper {

    /**
     * Converts a {@link Repository} to a {@link RepositoryDTO}.
     * <p>
     * This method takes a {@link Repository} object and maps its fields to a new {@link RepositoryDTO}
     * object. It includes copying the repository's name, fork status, owner login, and branches to the
     * DTO, allowing the data to be used in a format suitable for API responses.
     * </p>
     *
     * @param repo The {@link Repository} object to be converted.
     * @return A {@link RepositoryDTO} object with fields populated from the {@link Repository}.
     */
    public RepositoryDTO repoToDTO(RepositoryDTO repo) {
        RepositoryDTO repoDTO = new RepositoryDTO();
        repoDTO.setRepositoryName(repo.getRepositoryName());
        repoDTO.setForked(repo.isForked());
        repoDTO.setOwnerLogin(repo.getOwnerLogin());
        repoDTO.setBranches(repo.getBranches());
        return repoDTO;
    }

}
