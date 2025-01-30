package com.volvadvit.springdata.service.impl;

import com.volvadvit.springdata.entity.User;
import com.volvadvit.springdata.repository.UserRepository;
import com.volvadvit.springdata.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(final Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found for id: " + id));
    }
}
