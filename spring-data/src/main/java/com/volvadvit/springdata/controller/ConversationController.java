package com.volvadvit.springdata.controller;

import com.volvadvit.springdata.dto.response.ConversationResponseDTO;
import com.volvadvit.springdata.entity.Conversation;
import com.volvadvit.springdata.entity.Message;
import com.volvadvit.springdata.entity.User;
import com.volvadvit.springdata.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @GetMapping("/{id}")
    public ConversationResponseDTO getConversationById(@PathVariable final Integer id) {
        final Conversation conversation = conversationService.getConversationById(id);
        return toDTO(conversation);
    }

    private ConversationResponseDTO toDTO(final Conversation conversation) {
        return new ConversationResponseDTO(
                conversation.getId(),
                conversation.getName(),
                conversation.getUsers().stream().map(User::getId).collect(Collectors.toSet()),
                conversation.getMessages().stream().map(Message::getId).collect(Collectors.toSet())
        );
    }

}
