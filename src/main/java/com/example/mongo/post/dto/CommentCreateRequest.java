package com.example.mongo.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequest {
    private String postId;
    private String username;
    private String content;
}
