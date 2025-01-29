package com.volvadvit.springdata.controller;

import com.volvadvit.springdata.dto.response.UserResponseDTO;
import com.volvadvit.springdata.entity.Conversation;
import com.volvadvit.springdata.entity.Message;
import com.volvadvit.springdata.entity.User;
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

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> getUserById(final @PathVariable Integer id) {
        final User user = userService.getUserById(id);
        return ResponseEntity.ok(toUserResponseDTO(user));
    }

    private UserResponseDTO toUserResponseDTO(final User user) {
        return new UserResponseDTO(user.getId(), user.getName(),
                user.getSentMessages().stream().map(Message::getId).collect(Collectors.toSet()),
                user.getConversations().stream().map(Conversation::getId).collect(Collectors.toSet()));
    }
}
