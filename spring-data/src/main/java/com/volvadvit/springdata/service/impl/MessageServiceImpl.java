package com.volvadvit.springdata.service.impl;

import com.volvadvit.springdata.dto.request.MessageCreateRequestDTO;
import com.volvadvit.springdata.dto.request.MessageUpdateRequestDTO;
import com.volvadvit.springdata.entity.IdempotencyKey;
import com.volvadvit.springdata.entity.Message;
import com.volvadvit.springdata.repository.IdempotencyKeyRepository;
import com.volvadvit.springdata.repository.MessageRepository;
import com.volvadvit.springdata.service.ConversationService;
import com.volvadvit.springdata.service.MessageService;
import com.volvadvit.springdata.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
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
    private final IdempotencyKeyRepository idempotencyKeyRepository;

    @Override
    @Transactional
    public Message saveNewMessage(final MessageCreateRequestDTO dto) {
        final Message newMessage = new Message();
        newMessage.setBody(dto.getMessage());
        newMessage.setSender(userService.getUserById(dto.getSenderId()));
        newMessage.setConversation(conversationService.getConversationById(dto.getConversationId()));
        // Save without user_conversations update to simplify
        return messageRepository.save(newMessage);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public String updateMessageBody(final String idempotencyKey, final MessageUpdateRequestDTO dto) {
        final IdempotencyKey savedKey = idempotencyKeyRepository.findById(idempotencyKey).orElse(null);

        if (savedKey != null) {
            if (savedKey.getExpiryDate().isBefore(LocalDateTime.now())) {
                idempotencyKeyRepository.delete(savedKey);
            } else {
                return savedKey.getResponse();
            }
        }

        final Message newMessage = messageRepository.findById(dto.getMessageId())
                .orElseThrow(() -> new RuntimeException("Message not found"));
        newMessage.setBody(dto.getMessage());
        messageRepository.save(newMessage);
        final String response = "Message updated, with ID: " + newMessage.getId();
        saveIdempotencyKey(idempotencyKey, response);

        return response;
    }

    @Override
    public List<Message> getAllCreatedAfter(final String createdAfterDate) {
        final LocalDateTime localDate = LocalDateTime.parse("2018-05-05T11:50:55", DateTimeFormatter.ISO_DATE_TIME);
        return messageRepository.findAllByCreatedAtAfter(localDate);
    }

    private void saveIdempotencyKey(String idempotencyKey, String response) {
        final IdempotencyKey newKey = new IdempotencyKey();
        newKey.setKey(idempotencyKey);
        newKey.setResponse(response);
        newKey.setExpiryDate(LocalDateTime.now().plusHours(24)); // 24-hour expiration
        idempotencyKeyRepository.save(newKey);
    }
}
