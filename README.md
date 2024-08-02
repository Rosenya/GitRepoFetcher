# Git Repo Fetcher

## Description

Git Repo Fetcher is an application that connects with GitHub API, retrieves data about 
user repositories that are not forks, and returns it in JSON format including branch
and last commit information. In case of non existent user returns 404 http status 
code with message. 

## Project structure:

- model classes that mirror json structure of response from endpoint.
- service class that contains logic.
- controller class with dynamic GET request.
- `GitHubConnectionConfig` class, responsible for configuring the WebClient connection.
- DTO classes to enhance data security and map received JSON data.
- branch and repository mappers to ensure communication between model classes and DTO ones
  and to separate business logic.
- error response class to provide 404 status code response.
- tests results for `200 OK` and `404 NOT_FOUND` response.

## API Endpoints

### Retrieve Non-Forked Repositories

- **URL:** `/getNotForkedRepos/{owner}`
- **Method:** `GET`
- **Description:** Retrieves a list of all non-forked repositories for the specified user.
- **Response:** `200 OK` with a JSON body containing a list of non-forked repositories for the 
                specified user including branches and last commit information. 
- **Response:** `404 NOT_FOUND` with a JSON body containing status code and short 
                message in case of non existent user.

