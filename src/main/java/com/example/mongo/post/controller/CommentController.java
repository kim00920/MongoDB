package com.example.mongo.post.controller;

import com.example.mongo.post.dto.CommentCreateRequest;
import com.example.mongo.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@RequestBody CommentCreateRequest request) {
        commentService.addComment(request);
    }
}
