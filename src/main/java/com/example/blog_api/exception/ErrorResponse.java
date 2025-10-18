package com.example.blog_api.exception;

import java.time.Instant;
import java.util.List;

public class ErrorResponse {
    private Instant timestamp;
    private int status;
    private String message;
    private List<String> errors;

    public ErrorResponse() {}

    public ErrorResponse(String message, List<String> errors) {
        this.timestamp = Instant.now();
        this.status = 400;
        this.message = message;
        this.errors = errors;
    }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }
}
