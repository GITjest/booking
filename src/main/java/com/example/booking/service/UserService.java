package com.example.booking.service;

import com.example.booking.exception.UserNotFoundException;
import com.example.booking.model.User;
import com.example.booking.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
