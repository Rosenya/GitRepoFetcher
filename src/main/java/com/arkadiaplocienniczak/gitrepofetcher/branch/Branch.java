package com.arkadiaplocienniczak.gitrepofetcher.branch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch {

    @JsonProperty("name")
    private String name;
    @JsonProperty("commit")
    private Commit commit;

}
