package com.example.mongo.user.service;

import com.example.mongo.user.domain.User;
import com.example.mongo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 생성
    public User save(User user) {
        return userRepository.save(user);
    }

    // 전체 조회
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // 단건 조회
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("찾을 수 없는 회원"));
    }

    // 삭제
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
