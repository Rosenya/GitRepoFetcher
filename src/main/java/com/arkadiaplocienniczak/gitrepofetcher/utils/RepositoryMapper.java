package com.arkadiaplocienniczak.gitrepofetcher.utils;

import com.arkadiaplocienniczak.gitrepofetcher.model.Repository;
import com.arkadiaplocienniczak.gitrepofetcher.model.RepositoryDTO;
import org.springframework.stereotype.Component;

@Component
public class RepositoryMapper {

    public RepositoryDTO repoToDTO(RepositoryDTO repo) {
        RepositoryDTO repoDTO = new RepositoryDTO();
        repoDTO.setRepositoryName(repo.getRepositoryName());
        repoDTO.setForked(repo.isForked());
        repoDTO.setOwnerLogin(repo.getOwnerLogin());
        repoDTO.setBranches(repo.getBranches());
        return repoDTO;
    }

}
