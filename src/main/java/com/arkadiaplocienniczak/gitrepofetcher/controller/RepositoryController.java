package com.arkadiaplocienniczak.gitrepofetcher.controller;

import com.arkadiaplocienniczak.gitrepofetcher.model.RepositoryDTO;
import com.arkadiaplocienniczak.gitrepofetcher.service.RepositoryService;
import com.arkadiaplocienniczak.gitrepofetcher.utils.ErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class responsible for handling HTTP requests related to repositories.
 * <p>
 * This class provides an endpoint to retrieve a list of non-forked repositories
 * for a given GitHub user. It uses the {@link RepositoryService} to fetch the required
 * data and returns appropriate responses based on the presence of repositories.
 * </p>
 */

@AllArgsConstructor
@RestController
public class RepositoryController {

    /**
     * Service class responsible for the business logic related to repositories.
     */
    private final RepositoryService repositoryService;

    /**
     * Retrieves a list of non-forked repositories for the specified GitHub user.
     * <p>
     * This endpoint processes a GET request to the URL "/getNotForkedRepos/{owner}".
     * It uses the provided owner's username to fetch the list of repositories that
     * are not forks. If repositories are found, it returns a 200 OK status with the
     * list of repositories. If no repositories are found, it returns a 404 Not Found
     * status with an error message.
     * </p>
     *
     * @param owner The GitHub username for which to retrieve non-forked repositories.
     * @return A {@link ResponseEntity} containing the list of non-forked repositories
     *         or an error response if no repositories are found.
     */
    @GetMapping("/getNotForkedRepos/{owner}")
    public ResponseEntity getNotForkedRepos(@PathVariable String owner) {
        List<RepositoryDTO> repositoryDTO = repositoryService.getNotForkedRepos(owner);
        if (!repositoryDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(repositoryDTO);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("404", "No repositories found for the given owner");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}
