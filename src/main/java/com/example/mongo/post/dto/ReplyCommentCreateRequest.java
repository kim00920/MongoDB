package com.example.mongo.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyCommentCreateRequest {
    private String postId;
    private String commentId;
    private String username;
    private String content;
}
