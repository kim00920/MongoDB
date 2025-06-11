package com.example.mongo.post.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    private String id;

    private String username;
    private String content;

    @Builder.Default
    private List<ReplyComment> replies = new ArrayList<>();
}
