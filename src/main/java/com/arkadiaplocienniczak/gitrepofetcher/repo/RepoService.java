package com.arkadiaplocienniczak.gitrepofetcher.repo;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepoService {

    private Repo repo;

    public List<Repo> getAllRepos() {
        List<Repo> repoList = new ArrayList<>();
        repo.getName();
        repo.getOwnerName();
        repo.getBranches();
        repoList.add(repo);
        return repoList;
    }

    public List<Repo> getNoForkedRepos() {
        List<Repo> repoList = new ArrayList<>();
        repo.getName();
        repo.getOwnerName();
        repo.getBranches();
        repo.isForked();
        if(!repo.isForked()){
            repoList.add(repo);
        }
        return repoList;
    }
}
