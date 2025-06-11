package com.example.mongo.post.dto;

import lombok.*;

@Getter
@Setter
public class PostDummyCreateRequest {
    private String userId;
    private Integer count;
}
