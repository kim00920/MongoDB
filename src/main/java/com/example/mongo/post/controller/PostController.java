package com.example.mongo.post.controller;

import com.example.mongo.post.dto.PostCreateRequest;
import com.example.mongo.post.dto.PostDummyCreateRequest;
import com.example.mongo.post.dto.PostResponse;
import com.example.mongo.post.domain.Post;
import com.example.mongo.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post getPost(@PathVariable String id) {
        return postService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostCreateRequest request) {
        postService.savePost(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id) {
        postService.deleteById(id);
    }

    @GetMapping("/most")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse mostCommentPost() {
        return postService.findMostCommentPost();
    }


    @PostMapping("/dummy")
    @ResponseStatus(HttpStatus.OK)
    public void savePostDummy(@RequestBody PostDummyCreateRequest request) {
        postService.savePostDummy(request);
    }

    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public Page<Post> getAllPosts(Pageable pageable) {
        return postService.findAll(pageable);
    }

}
