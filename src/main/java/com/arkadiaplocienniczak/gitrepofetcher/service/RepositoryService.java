package com.arkadiaplocienniczak.gitrepofetcher.service;

import com.arkadiaplocienniczak.gitrepofetcher.model.BranchDTO;
import com.arkadiaplocienniczak.gitrepofetcher.model.RepositoryDTO;
import com.arkadiaplocienniczak.gitrepofetcher.utils.BranchMapper;
import com.arkadiaplocienniczak.gitrepofetcher.utils.RepositoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.function.Consumer;

/**
 * Service class for interacting with the GitHub API to retrieve repository data.
 * <p>
 * This service handles fetching information about repositories from GitHub,
 * filtering out forked repositories, and enriching the repository data with branch information.
 * </p>
 */
@Service
@AllArgsConstructor
public class RepositoryService {

    private final WebClient webClient;
    private final RepositoryMapper repositoryMapper;
    private final BranchMapper branchMapper;

    /**
     * Retrieves a list of non-forked repositories for a specified user.
     * <p>
     * This method fetches the list of repositories for the given user,
     * filters out the forked repositories, and enriches the remaining repositories
     * with branch information. The final list of non-forked repositories is returned.
     * </p>
     *
     * @param owner The GitHub username of the repository owner.
     * @return A list of {@link RepositoryDTO} objects representing non-forked repositories.
     */
    public List<RepositoryDTO> getNotForkedRepos(String owner) {
        List<RepositoryDTO> notForkedRepositoryList = getRepositoryList(owner);
        notForkedRepositoryList = notForkedRepositoryList.stream()
                .map(repositoryMapper::repoToDTO)
                .filter(repositoryDTO -> !repositoryDTO.isForked())
                .toList();
        notForkedRepositoryList.forEach(getRepositoryDTOConsumer(owner));
        return notForkedRepositoryList;
    }

    /**
     * Fetches a list of repositories for a specified user from GitHub.
     * <p>
     * This method makes a GET request to the GitHub API to retrieve the list of repositories
     * for the given user. It includes parameters to specify the type of repositories and
     * the number of repositories to return per page.
     * </p>
     *
     * @param owner The GitHub username of the repository owner.
     * @return A list of {@link RepositoryDTO} objects representing repositories of the user.
     */
    List<RepositoryDTO> getRepositoryList(String owner) {
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

    /**
     * Fetches a list of branches for a specified repository from GitHub.
     * <p>
     * This method makes a GET request to the GitHub API to retrieve the list of branches
     * for the given repository.
     * </p>
     *
     * @param owner    The GitHub username of the repository owner.
     * @param repoName The name of the repository.
     * @return A list of {@link BranchDTO} objects representing branches of the repository.
     */
    List<BranchDTO> getBranchesForRepo(String owner, String repoName) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/repos/{owner}/{repo}/branches")
                        .build(owner, repoName))
                .retrieve()
                .bodyToFlux(BranchDTO.class)
                .collectList()
                .block();
    }

    /**
     * Provides a {@link Consumer} to enrich {@link RepositoryDTO} objects with branch information.
     * <p>
     * This method creates a consumer that fetches branch information for each repository
     * and sets the branches in the {@link RepositoryDTO}.
     * </p>
     *
     * @param owner The GitHub username of the repository owner.
     * @return A {@link Consumer} that processes {@link RepositoryDTO} objects and sets their branches.
     */
    Consumer<RepositoryDTO> getRepositoryDTOConsumer(String owner) {
        return repositoryDTO -> {
            List<BranchDTO> branches = getBranchesForRepo(owner, repositoryDTO.getRepositoryName());
            branches.stream()
                    .map(branchMapper::branchDTOToBranch)
                    .toList();
            repositoryDTO.setBranches(branches);
        };
    }

}

