package com.volvadvit.springdata.controller;

import com.volvadvit.springdata.controller.dto.UserDTO;
import com.volvadvit.springdata.entity.User;
import com.volvadvit.springdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(final @PathVariable Integer id) {
        final User user = userService.getUserById(id);
        return ResponseEntity.ok(
                new UserDTO(user.getId(), user.getName(),
                        user.getSentMessages().stream().map(msg -> msg.getId()).collect(Collectors.toSet()),
                        user.getConversations().stream().map(cnv -> cnv.getId()).collect(Collectors.toSet())));
    }
}
