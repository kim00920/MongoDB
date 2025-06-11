package com.example.mongo.post.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private String id;
    private String title;
    private String content;
    private String username;
    private int totalCommentCount;
}
