package com.volvadvit.springdata.controller;

import com.volvadvit.springdata.mapper.ConversationDtoMapper;
import com.volvadvit.springdata.model.dto.response.ConversationResponseDTO;
import com.volvadvit.springdata.model.entity.Conversation;
import com.volvadvit.springdata.model.entity.Message;
import com.volvadvit.springdata.model.entity.User;
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
    private final ConversationDtoMapper conversationDtoMapper;

    @GetMapping("/{id}")
    public ConversationResponseDTO getConversationById(@PathVariable final Integer id) {
        final Conversation conversation = conversationService.getConversationById(id);
        return conversationDtoMapper.toConversationResponseDTO(conversation);
    }
}
