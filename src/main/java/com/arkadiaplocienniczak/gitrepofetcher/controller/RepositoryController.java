package com.arkadiaplocienniczak.gitrepofetcher.controller;

import com.arkadiaplocienniczak.gitrepofetcher.model.RepositoryDTO;
import com.arkadiaplocienniczak.gitrepofetcher.service.RepositoryService;
import com.arkadiaplocienniczak.gitrepofetcher.utils.ErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class RepositoryController {

    private final RepositoryService repositoryService;

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
