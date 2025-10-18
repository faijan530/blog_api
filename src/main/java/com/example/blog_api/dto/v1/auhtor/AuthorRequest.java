package com.example.blog_api.dto.v1.auhtor;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthorRequest {
    @NotBlank(message = "name is required")
    private String name;

    @Email(message = "invalid email")
    private String email;

    private String bio;

    public AuthorRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}
