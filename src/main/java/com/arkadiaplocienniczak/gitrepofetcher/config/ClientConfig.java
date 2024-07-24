package com.arkadiaplocienniczak.gitrepofetcher.config;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class ClientConfig {

    @Value("${github.api.url}")
    private String gitHubApiUrl;

    @Value("${github.token}")
    private String token;

    public ClientConfig() {
        this.gitHubApiUrl = System.getenv("GITHUB_API_URL");
        this.token = System.getenv("GITHUB_TOKEN");
        if (this.gitHubApiUrl == null || this.token == null) {
            throw new RuntimeException("GITHUB_API_URL or GITHUB_TOKEN environment variable not set");
        }
    }

    public JSONArray getOwnerRepos(String owner) throws Exception {
        String url = gitHubApiUrl + "/users/" + owner + "/repos?type=owner&per_page=100";
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "token " + token);
        conn.setRequestProperty("Accept", "application/json");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return new JSONArray(response.toString());
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("status", responseCode);
            errorResponse.put("message", "Owner not found");
            throw new RuntimeException(errorResponse.toString());
        } else {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }
    }

}