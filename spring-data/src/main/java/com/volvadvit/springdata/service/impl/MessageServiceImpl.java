package com.volvadvit.springdata.service.impl;

import com.volvadvit.springdata.entity.Message;
import com.volvadvit.springdata.repository.MessageRepository;
import com.volvadvit.springdata.service.ConversationService;
import com.volvadvit.springdata.service.MessageService;
import com.volvadvit.springdata.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final ConversationService conversationService;

    public MessageServiceImpl(final MessageRepository messageRepository, final UserService userService, final ConversationService conversationService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.conversationService = conversationService;
    }

    @Override
    @Transactional
    public Message saveNewMessage(final String message, final Integer conversationId, final Integer senderId) {
        final Message newMessage = new Message();
        newMessage.setBody(message);
        newMessage.setSender(userService.getUserById(senderId));
        newMessage.setConversation(conversationService.getConversationById(conversationId));
        return messageRepository.save(newMessage);
    }

    @Override
    public List<Message> getAllCreatedAfter(final String createdAfterDate) {
        final LocalDateTime localDate = LocalDateTime.parse("2018-05-05T11:50:55", DateTimeFormatter.ISO_DATE_TIME);
        return messageRepository.findAllByCreatedAtAfter(localDate);
    }
}
