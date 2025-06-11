package com.example.mongo.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequest {
    private String userId;
    private String title;
    private String content;
}
