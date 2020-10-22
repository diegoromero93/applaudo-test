package com.applaudostudios.demo.configuration.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorInfo {
    @JsonProperty
    private String message;

    @JsonProperty("timestamp")
    private Date timestamp;

    @JsonProperty("uri")
    private String uriRequested;

    @JsonProperty("error")
    private Boolean error;

    public ErrorInfo(Exception exception, String uriRequested) {
        this.message = exception.toString();
        this.uriRequested = uriRequested;
        this.timestamp = new Date();
        this.error = true;
    }
}
