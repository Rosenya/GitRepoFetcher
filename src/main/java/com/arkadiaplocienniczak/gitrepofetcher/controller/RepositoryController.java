package com.arkadiaplocienniczak.gitrepofetcher.controller;

import com.arkadiaplocienniczak.gitrepofetcher.model.RepositoryDTO;
import com.arkadiaplocienniczak.gitrepofetcher.service.RepositoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class RepositoryController {

    private final RepositoryService repositoryService;

    @GetMapping("/getNotForkedRepos/{owner}")
    public ResponseEntity<List<RepositoryDTO>> getNotForkedRepos(@PathVariable String owner) {
        List<RepositoryDTO> repositoryDTO = repositoryService.getNotForkedRepos(owner);
        if (!repositoryDTO.isEmpty()) {
            return new ResponseEntity<>(repositoryDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
