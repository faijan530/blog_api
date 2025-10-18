package com.example.blog_api.controller.v1;

import com.example.blog_api.dto.v1.PostRequestV1;
import com.example.blog_api.dto.v1.PostResponseV1;
import com.example.blog_api.mapper.MapperUtils;
import com.example.blog_api.model.Post;
import com.example.blog_api.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/posts")
public class PostControllerV1 {

    private final PostService postService;

    public PostControllerV1(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponseV1> create(@Valid @RequestBody PostRequestV1 req) {
        Post saved = postService.createFromV1(req);
        return ResponseEntity.ok(MapperUtils.toV1(saved));
    }

    @GetMapping
    public ResponseEntity<List<PostResponseV1>> getAll() {
        List<PostResponseV1> list = postService.getAll().stream()
                .map(MapperUtils::toV1)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseV1> getById(@PathVariable Long id) {
        Post p = postService.getById(id);
        return ResponseEntity.ok(MapperUtils.toV1(p));
    }
}
