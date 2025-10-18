package com.example.blog_api.controller.v1.v2;


import com.example.blog_api.dto.v1.v2.PostRequestV2;
import com.example.blog_api.dto.v1.v2.PostResponseV2;
import com.example.blog_api.mapper.MapperUtils;
import com.example.blog_api.model.Post;
import com.example.blog_api.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/posts")
public class PostControllerV2 {

    private final PostService postService;

    public PostControllerV2(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponseV2> create(@Valid @RequestBody PostRequestV2 req) {
        Post saved = postService.createFromV2(req);
        return ResponseEntity.ok(MapperUtils.toV2(saved));
    }

    // public posts only (published)
    @GetMapping
    public ResponseEntity<List<PostResponseV2>> getPublic() {
        List<PostResponseV2> list = postService.getAllPublic().stream()
                .map(MapperUtils::toV2)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseV2> getById(@PathVariable Long id) {
        Post p = postService.getById(id);
        return ResponseEntity.ok(MapperUtils.toV2(p));
    }

    // admin-like: publish
    @PostMapping("/{id}/publish")
    public ResponseEntity<PostResponseV2> publish(@PathVariable Long id) {
        Post p = postService.publish(id);
        return ResponseEntity.ok(MapperUtils.toV2(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
