package com.jabguru.TwitterClone.user.impl;

import com.jabguru.TwitterClone.user.User;
import com.jabguru.TwitterClone.user.UserRepository;
import com.jabguru.TwitterClone.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> searchUserByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }
}
