package com.arkadiaplocienniczak.gitrepofetcher.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    @JsonProperty
    private String status;
    @JsonProperty
    private String message;

}
