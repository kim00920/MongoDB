package com.example.mongo.post.service;

import com.example.mongo.post.dto.ReplyCommentCreateRequest;
import com.example.mongo.post.domain.Comment;
import com.example.mongo.post.domain.Post;
import com.example.mongo.post.domain.ReplyComment;
import com.example.mongo.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReplyCommentService {

    private final PostRepository postRepository;

    // 대댓글 생성
    public void addReplyComment(ReplyCommentCreateRequest request) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        Comment targetComment = post.getComments().stream()
                .filter(comment -> comment.getId() != null && comment.getId().equals(request.getCommentId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));

        ReplyComment reply = ReplyComment.builder()
                .id(UUID.randomUUID().toString())
                .username(request.getUsername())
                .content(request.getContent())
                .build();

        targetComment.getReplies().add(reply);
        postRepository.save(post);
    }
}
