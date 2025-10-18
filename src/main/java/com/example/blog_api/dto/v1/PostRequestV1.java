package com.example.blog_api.dto.v1;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PostRequestV1 {
    @NotBlank(message = "title must not be blank")
    private String title;

    @NotBlank(message = "content must not be blank")
    private String content;

    @NotNull(message = "authorId is required")
    private Long authorId;

    public PostRequestV1() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
}
