package com.example.mongo.post.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "post")
public class Post {
    @Id
    private String id;

    private String title;
    private String content;
    private String username;

    @Indexed(direction = IndexDirection.DESCENDING)
    private LocalDateTime createdAt;

    private List<Comment> comments;
}
