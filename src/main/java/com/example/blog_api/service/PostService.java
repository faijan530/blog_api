package com.example.blog_api.service;

import com.example.blog_api.dto.v1.PostRequestV1;
import com.example.blog_api.dto.v1.v2.PostRequestV2;
import com.example.blog_api.exception.ResourceNotFoundException;
import com.example.blog_api.mapper.MapperUtils;
import com.example.blog_api.model.Author;
import com.example.blog_api.model.Post;
import com.example.blog_api.model.PostStatus;
import com.example.blog_api.repository.AuthorRepository;
import com.example.blog_api.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostService(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Post createFromV1(PostRequestV1 req) {
        Author author = authorRepository.findById(req.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found: " + req.getAuthorId()));
        Post p = new Post();
        p.setTitle(req.getTitle());
        p.setContent(req.getContent());
        p.setAuthor(author);
        p.setStatus(PostStatus.DRAFT);
        return postRepository.save(p);
    }

    @Transactional
    public Post createFromV2(PostRequestV2 req) {
        Author author = authorRepository.findById(req.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found: " + req.getAuthorId()));
        Post p = new Post();
        p.setTitle(req.getTitle());
        p.setContent(req.getContent());
        p.setAuthor(author);
        if (req.getTags() != null) {
            p.setTags(MapperUtils.listToCsv(req.getTags()));
        }
        if (req.getStatus() != null) {
            try {
                p.setStatus(PostStatus.valueOf(req.getStatus().toUpperCase()));
            } catch (IllegalArgumentException ex) {
                p.setStatus(PostStatus.DRAFT);
            }
        } else {
            p.setStatus(PostStatus.DRAFT);
        }
        return postRepository.save(p);
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public List<Post> getAllPublic() {
        return postRepository.findByStatus(PostStatus.PUBLISHED);
    }

    public Post getById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found: " + id));
    }

    @Transactional
    public void delete(Long id) {
        Post p = getById(id);
        postRepository.delete(p);
    }

    @Transactional
    public Post publish(Long id) {
        Post p = getById(id);
        p.setStatus(PostStatus.PUBLISHED);
        return postRepository.save(p);
    }
}
