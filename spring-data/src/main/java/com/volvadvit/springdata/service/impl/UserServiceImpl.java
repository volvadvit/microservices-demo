package com.volvadvit.springdata.service.impl;

import com.volvadvit.springdata.entity.User;
import com.volvadvit.springdata.exception.UserNotFoundException;
import com.volvadvit.springdata.repository.UserRepository;
import com.volvadvit.springdata.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(final Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
