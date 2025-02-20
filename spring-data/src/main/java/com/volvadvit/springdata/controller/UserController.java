package com.volvadvit.springdata.controller;

import com.volvadvit.springdata.mapper.UserDtoMapper;
import com.volvadvit.springdata.model.dto.response.UserResponseDTO;
import com.volvadvit.springdata.model.entity.Conversation;
import com.volvadvit.springdata.model.entity.Message;
import com.volvadvit.springdata.model.entity.User;
import com.volvadvit.springdata.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> getUserById(final @PathVariable Integer id) {
        final User user = userService.getUserById(id);
        return ResponseEntity.ok(userDtoMapper.toUserResponseDTO(user));
    }
}
