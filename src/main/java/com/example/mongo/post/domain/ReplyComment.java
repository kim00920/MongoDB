package com.example.mongo.post.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyComment {

    @Id
    private String id;

    private String username;    // 대댓글 작성자 이름
    private String content;     // 대댓글 내용
}
