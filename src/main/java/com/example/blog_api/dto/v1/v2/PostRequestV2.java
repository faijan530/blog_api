package com.example.blog_api.dto.v1.v2;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class PostRequestV2 {
    @NotBlank(message = "title must not be blank")
    private String title;

    @NotBlank(message = "content must not be blank")
    private String content;

    @NotNull(message = "authorId is required")
    private Long authorId;

    @Size(max = 10, message = "max 10 tags allowed")
    private List<@NotBlank(message = "tag must not be blank") String> tags;

    // optional; if provided it must be one of DRAFT/PUBLISHED/ARCHIVED
    private String status;

    public PostRequestV2() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
