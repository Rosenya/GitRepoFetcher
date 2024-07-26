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

//    public Repository repoDTOToRepo(RepositoryDTO repoDTO) {
//        Repository repo = new Repository();
//        repo.setRepositoryName(repoDTO.getRepositoryName());
//        repo.setForked(repoDTO.isForked());
//        repo.setOwnerLogin(repoDTO.getOwnerLogin());
//        repo.setBranches(repoDTO.getBranches());
//        return repo;
//    }

}
