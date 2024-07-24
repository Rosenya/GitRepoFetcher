package com.arkadiaplocienniczak.gitrepofetcher.repo;

import com.arkadiaplocienniczak.gitrepofetcher.branch.Branch;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepoService {

    private WebClient webClient;

    public RepoService(WebClient webClient) {
        this.webClient = webClient;
    }


    public List<Repo> getAllRepos(String owner) {
        List<Repo> allRepoList = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/{owner}/repos")
                        .queryParam("type", "owner")
                        .queryParam("per_page", 100)
                        .build(owner))
                .retrieve()
                .bodyToFlux(Repo.class)
                .collectList()
                .block();
        return allRepoList.stream()
                .map(repo -> {
                    List<Branch> branches = getBranchesForRepo(owner, repo.getRepositoryName());
                    repo.setBranches(branches);
                    return repo;
                }).collect(Collectors.toList());
    }


    public List<Repo> getNotForkedRepos(String owner) {
        List<Repo> notForkedRepoList = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/{owner}/repos")
                        .queryParam("type", "owner")
                        .queryParam("per_page", 100)
                        .build(owner))
                .retrieve()
                .bodyToFlux(Repo.class)
                .collectList()
                .block();
        return notForkedRepoList.stream()
                .filter(repo -> !repo.isForked())
                .map(repo -> {
                    List<Branch> branches = getBranchesForRepo(owner, repo.getRepositoryName());
                    repo.setBranches(branches);
                    return repo;
                }).collect(Collectors.toList());
    }

    private List<Branch> getBranchesForRepo(String owner, String repoName) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/repos/{owner}/{repo}/branches")
                        .build(owner, repoName))
                .retrieve()
                .bodyToFlux(Branch.class)
                .collectList()
                .block();
    }

}
