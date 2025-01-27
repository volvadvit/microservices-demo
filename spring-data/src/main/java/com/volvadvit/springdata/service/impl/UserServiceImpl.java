package com.volvadvit.springdata.service.impl;

import com.volvadvit.springdata.entity.User;
import com.volvadvit.springdata.exception.UserNotFoundException;
import com.volvadvit.springdata.repository.UserRepository;
import com.volvadvit.springdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(final Long id) {
        final Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException(id);
        }
    }
}
