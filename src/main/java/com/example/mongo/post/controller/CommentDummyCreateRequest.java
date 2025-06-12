package com.example.mongo.post.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDummyCreateRequest {
    private String postId;
    private Integer count;
}
