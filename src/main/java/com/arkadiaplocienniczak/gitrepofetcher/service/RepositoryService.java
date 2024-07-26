package com.arkadiaplocienniczak.gitrepofetcher.service;


import com.arkadiaplocienniczak.gitrepofetcher.model.BranchDTO;
import com.arkadiaplocienniczak.gitrepofetcher.model.RepositoryDTO;
import com.arkadiaplocienniczak.gitrepofetcher.utils.BranchMapper;
import com.arkadiaplocienniczak.gitrepofetcher.utils.RepositoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepositoryService {

    private final WebClient webClient;
    private final RepositoryMapper repositoryMapper;
    private final BranchMapper branchMapper;

//    public List<RepositoryDTO> getNotForkedRepos(String owner) {
//        List<RepositoryDTO> notForkedRepositoryList = getRepositoryList(owner);
//        return notForkedRepositoryList.stream()
//                .filter(repositoryDTO -> !repositoryDTO.isForked())
//                .map(repositoryDTO -> {
//                    List<BranchDTO> branches = getBranchesForRepo(owner, repositoryDTO.getRepositoryName());
//                    repositoryDTO.setBranches(branches);
//                    return repositoryDTO;
//                })
//                .map((RepositoryDTO repo) -> repositoryMapper.repoToDTO(repo))
//                .collect(Collectors.toList());
//    }

    public List<RepositoryDTO> getNotForkedRepos(String owner) {
        List<RepositoryDTO> notForkedRepositoryList = getRepositoryList(owner);


        notForkedRepositoryList = notForkedRepositoryList.stream()
                .filter(repositoryDTO -> !repositoryDTO.isForked())
                .toList();

        notForkedRepositoryList.forEach(repositoryDTO -> {
            List<BranchDTO> branches = getBranchesForRepo(owner, repositoryDTO.getRepositoryName());
            repositoryDTO.setBranches(branches);
        });


        return notForkedRepositoryList;
    }

    private List<RepositoryDTO> getRepositoryList(String owner) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/{owner}/repos")
                        .queryParam("type", "owner")
                        .queryParam("per_page", 100)
                        .build(owner))
                .retrieve()
                .bodyToFlux(RepositoryDTO.class)
                .collectList()
                .block();
    }

    private List<BranchDTO> getBranchesForRepo(String owner, String repoName) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/repos/{owner}/{repo}/branches")
                        .build(owner, repoName))
                .retrieve()
                .bodyToFlux(BranchDTO.class)
                .collectList()
                .block();
    }

}
