package com.example.blog_api.repository;

import com.example.blog_api.model.Post;
import com.example.blog_api.model.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByStatus(PostStatus status);
    List<Post> findByAuthorId(Long authorId);
}
