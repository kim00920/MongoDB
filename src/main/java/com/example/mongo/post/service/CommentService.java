package com.example.mongo.post.service;

import com.example.mongo.post.dto.CommentCreateRequest;
import com.example.mongo.post.domain.Comment;
import com.example.mongo.post.domain.Post;
import com.example.mongo.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;

    // 댓글 생성
    public void addComment(CommentCreateRequest request) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        Comment comment = Comment.builder()
                .id(UUID.randomUUID().toString())
                .username(request.getUsername())
                .content(request.getContent())
                .build();

        post.getComments().add(comment);
        postRepository.save(post);
    }

}
