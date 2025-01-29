package com.volvadvit.springdata.service.impl;

import com.volvadvit.springdata.dto.request.MessageRequestDTO;
import com.volvadvit.springdata.entity.Message;
import com.volvadvit.springdata.repository.MessageRepository;
import com.volvadvit.springdata.service.ConversationService;
import com.volvadvit.springdata.service.MessageService;
import com.volvadvit.springdata.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final ConversationService conversationService;

    @Override
    @Transactional
    public Message saveNewMessage(final MessageRequestDTO dto) {
        final Message newMessage = new Message();
        newMessage.setBody(dto.getMessage());
        newMessage.setSender(userService.getUserById(dto.getSenderId()));
        newMessage.setConversation(conversationService.getConversationById(dto.getConversationId()));
        // Save without user_conversations update
        return messageRepository.save(newMessage);
    }

    @Override
    public List<Message> getAllCreatedAfter(final String createdAfterDate) {
        final LocalDateTime localDate = LocalDateTime.parse("2018-05-05T11:50:55", DateTimeFormatter.ISO_DATE_TIME);
        return messageRepository.findAllByCreatedAtAfter(localDate);
    }
}
