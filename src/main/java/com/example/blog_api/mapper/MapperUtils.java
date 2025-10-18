package com.example.blog_api.mapper;


import com.example.blog_api.dto.v1.PostResponseV1;
import com.example.blog_api.dto.v1.v2.PostResponseV2;
import com.example.blog_api.model.Post;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MapperUtils {

    public static PostResponseV1 toV1(Post p) {
        if (p == null) return null;
        PostResponseV1 r = new PostResponseV1();
        r.setId(p.getId());
        r.setTitle(p.getTitle());
        r.setContent(p.getContent());
        r.setAuthorId(p.getAuthor() != null ? p.getAuthor().getId() : null);
        r.setAuthorName(p.getAuthor() != null ? p.getAuthor().getName() : null);
        return r;
    }

    public static PostResponseV2 toV2(Post p) {
        if (p == null) return null;
        PostResponseV2 r = new PostResponseV2();
        r.setId(p.getId());
        r.setTitle(p.getTitle());
        r.setContent(p.getContent());
        r.setAuthorId(p.getAuthor() != null ? p.getAuthor().getId() : null);
        r.setAuthorName(p.getAuthor() != null ? p.getAuthor().getName() : null);
        r.setStatus(p.getStatus() != null ? p.getStatus().name() : null);
        r.setCreatedAt(p.getCreatedAt());
        r.setUpdatedAt(p.getUpdatedAt());
        r.setTags(csvToList(p.getTags()));
        return r;
    }

    public static String listToCsv(List<String> tags) {
        if (tags == null || tags.isEmpty()) return null;
        return tags.stream().map(String::trim).collect(Collectors.joining(","));
    }

    public static List<String> csvToList(String csv) {
        if (csv == null || csv.isBlank()) return Collections.emptyList();
        return Arrays.stream(csv.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
