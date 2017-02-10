package com.example.service;

import com.example.domain.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Created by hokyeong on 2017. 2. 10..
 */
@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserBoard(String string) {
        return userRepository.findByUserName(string);
    }
}
