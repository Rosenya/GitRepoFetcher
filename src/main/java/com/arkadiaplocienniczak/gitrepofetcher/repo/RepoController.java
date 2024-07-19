package com.arkadiaplocienniczak.gitrepofetcher.repo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class RepoController {

    private final RepoService repoService;

    @GetMapping("/getAllRepos/{owner}")
    public List<Repo> getAllRepos(@PathVariable String owner) {
        return repoService.getAllRepos();
    }

    @GetMapping("/getNotForkedRepos/{owner}")
    public List<Repo> getNotForkedRepos(@PathVariable String owner) {
        return repoService.getNoForkedRepos();
    }

}
