package com.arkadiaplocienniczak.gitrepofetcher.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents an error response returned by the API.
 * <p>
 * This class is used to structure the error responses sent back to the client
 * when an error occurs in the application. It includes a status code and a message
 * that describes the error.
 * </p>
 */
@Data
@AllArgsConstructor
public class ErrorResponse {

    /**
     * The status code of the error.
     * <p>
     * This field represents the HTTP status code associated with the error,
     * such as "404" for "Not Found" or "400" for "Bad Request". It is serialized
     * to JSON using the {@link JsonProperty} annotation.
     * </p>
     */
    @JsonProperty
    private String status;

    /**
     * A message describing the error.
     * <p>
     * This field contains a human-readable message that provides more details
     * about the error. It is intended to help clients understand what went wrong.
     * It is serialized to JSON using the {@link JsonProperty} annotation.
     * </p>
     */
    @JsonProperty
    private String message;

}
