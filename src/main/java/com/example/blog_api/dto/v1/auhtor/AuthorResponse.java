package com.example.blog_api.dto.v1.auhtor;


public class AuthorResponse {
    private Long id;
    private String name;
    private String email;
    private String bio;

    public AuthorResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}
