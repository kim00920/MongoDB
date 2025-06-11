package com.example.mongo.post.controller;

import com.example.mongo.post.dto.ReplyCommentCreateRequest;
import com.example.mongo.post.service.ReplyCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/replyComment")
public class ReplyCommentController {

    private final ReplyCommentService replyCommentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addReply(@RequestBody ReplyCommentCreateRequest request) {
        replyCommentService.addReplyComment(request);
    }
}
