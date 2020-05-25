package com.academiaspring.demo.services;

import com.academiaspring.demo.models.User;
import com.academiaspring.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
    public Optional<User> createUser(User user) {
        return Optional.of(userRepository.save(user));
    }
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
