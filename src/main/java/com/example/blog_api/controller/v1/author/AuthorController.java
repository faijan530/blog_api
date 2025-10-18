package com.example.blog_api.controller.v1.author;

import com.example.blog_api.dto.v1.auhtor.AuthorRequest;
import com.example.blog_api.dto.v1.auhtor.AuthorResponse;
import com.example.blog_api.model.Author;
import com.example.blog_api.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // Create a new author
    @PostMapping
    public ResponseEntity<AuthorResponse> create(@Valid @RequestBody AuthorRequest req) {
        Author a = authorService.create(req);
        return ResponseEntity.ok(toResponse(a));
    }

    // Get all authors
    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAll() {
        List<AuthorResponse> list = authorService.getAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // Get author by ID
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getById(@PathVariable Long id) {
        Author a = authorService.getById(id);
        return ResponseEntity.ok(toResponse(a));
    }

    // Update author by ID
    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(@PathVariable Long id,
                                                 @Valid @RequestBody AuthorRequest req) {
        Author a = authorService.update(id, req);
        return ResponseEntity.ok(toResponse(a));
    }

    // Delete author by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Mapper method: convert Author entity to AuthorResponse DTO
    private AuthorResponse toResponse(Author a) {
        AuthorResponse r = new AuthorResponse();
        r.setId(a.getId());
        r.setName(a.getName());
        r.setEmail(a.getEmail());
        r.setBio(a.getBio());
        return r;
    }
}
