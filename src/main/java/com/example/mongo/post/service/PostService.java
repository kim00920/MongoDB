package com.example.mongo.post.service;

import com.example.mongo.post.dto.PostCreateRequest;
import com.example.mongo.post.dto.PostDummyCreateRequest;
import com.example.mongo.post.dto.PostResponse;
import com.example.mongo.post.domain.Post;
import com.example.mongo.post.repository.PostRepository;
import com.example.mongo.user.domain.User;
import com.example.mongo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    // private final MongoTemplate mongoTemplate;

    // 생성
    public void savePost(PostCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않음"));

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .username(user.getName())
                .comments(new ArrayList<>())
                .build();

        postRepository.save(post);
    }

    // 단건 조회
    public Post findById(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
    }

    // 전체 조회
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // 삭제
    public void deleteById(String id) {
        postRepository.deleteById(id);
    }

    // 댓글 수가 가장 많은 게시글
    public PostResponse findMostCommentPost() {
        List<Post> posts = postRepository.findAll();

        Post post = findTotalComment(posts);

        int totalCommentCount = sumComment(post);

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .username(post.getUsername())
                .totalCommentCount(totalCommentCount)
                .build();
    }

    // 게시글 더미 데이터 추가
    public void savePostDummy(PostDummyCreateRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않음"));

        List<Post> dummyPosts = new ArrayList<>();

        for (int i = 1; i <= request.getCount(); i++) {
            Post post = Post.builder()
                    .title("더미 게시글 제목 " + i)
                    .content("더미 게시글 내용 " + i)
                    .username(user.getName())
                    .comments(new ArrayList<>())
                    .createdAt(LocalDateTime.now())
                    .build();

            dummyPosts.add(post);
        }

        postRepository.saveAll(dummyPosts);
    }

    // 페이징 전체 조회
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    //
    private Post findTotalComment(List<Post> posts) {
        return posts.stream()
                .max(Comparator.comparingInt(a ->
                        a.getComments().stream()
                                .mapToInt(c -> 1 + c.getReplies().size())
                                .sum()
                ))
                .orElse(null);
    }

    // 댓글 + 대댓글 개수 리턴
    private int sumComment(Post post) {
        return post.getComments().stream()
                .mapToInt(comment -> 1 + comment.getReplies().size())
                .sum();
    }

}
