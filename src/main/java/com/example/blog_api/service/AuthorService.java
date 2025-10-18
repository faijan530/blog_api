package com.example.blog_api.service;


import com.example.blog_api.dto.v1.auhtor.AuthorRequest;
import com.example.blog_api.exception.ResourceNotFoundException;
import com.example.blog_api.model.Author;
import com.example.blog_api.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository repo;

    public AuthorService(AuthorRepository repo) {
        this.repo = repo;
    }

    public Author create(AuthorRequest req) {
        Author a = new Author();
        a.setName(req.getName());
        a.setEmail(req.getEmail());
        a.setBio(req.getBio());
        return repo.save(a);
    }

    public List<Author> getAll() {
        return repo.findAll();
    }

    public Author getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found: " + id));
    }

    @Transactional
    public Author update(Long id, AuthorRequest req) {
        Author a = getById(id);
        if (req.getName() != null) a.setName(req.getName());
        if (req.getEmail() != null) a.setEmail(req.getEmail());
        if (req.getBio() != null) a.setBio(req.getBio());
        return repo.save(a);
    }

    @Transactional
    public void delete(Long id) {
        Author a = getById(id);
        repo.delete(a);
    }
}
