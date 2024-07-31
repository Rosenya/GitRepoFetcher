package com.arkadiaplocienniczak.gitrepofetcher.service;


import com.arkadiaplocienniczak.gitrepofetcher.model.BranchDTO;
import com.arkadiaplocienniczak.gitrepofetcher.model.RepositoryDTO;
import com.arkadiaplocienniczak.gitrepofetcher.utils.BranchMapper;
import com.arkadiaplocienniczak.gitrepofetcher.utils.RepositoryMapper;
import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
public class RepositoryService {

    private final WebClient webClient;
    private final RepositoryMapper repositoryMapper;
    private final BranchMapper branchMapper;

    public List<RepositoryDTO> getNotForkedRepos(String owner) {
        List<RepositoryDTO> notForkedRepositoryList = getRepositoryList(owner);
        notForkedRepositoryList = notForkedRepositoryList.stream()
                .map(repositoryMapper::repoToDTO)
                .filter(repositoryDTO -> !repositoryDTO.isForked())
                .toList();
        notForkedRepositoryList.forEach(getRepositoryDTOConsumer(owner));
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
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                            Mono.error(new RuntimeException("Client error: " + response.statusCode()))
                )
                .onStatus(HttpStatusCode::is5xxServerError,  response ->
                        Mono.error(new RuntimeException("Server error: " + response.statusCode()))
                )
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

    private Consumer<RepositoryDTO> getRepositoryDTOConsumer(String owner) {
        return repositoryDTO -> {
            List<BranchDTO> branches = getBranchesForRepo(owner, repositoryDTO.getRepositoryName());
            branches.stream()
                    .map(branchMapper::branchDTOToBranch)
                    .toList();
            repositoryDTO.setBranches(branches);
        };
    }

}

