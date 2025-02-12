package com.volvadvit.springdata.service;

import com.volvadvit.springdata.dto.request.MessageCreateRequestDTO;
import com.volvadvit.springdata.dto.request.MessageUpdateRequestDTO;
import com.volvadvit.springdata.entity.Message;

import java.util.List;

public interface MessageService {

    /**
     * Save new message and return it back with generated ID
     *
     * @param dto details of the new message
     * @return message with generated ID
     */
    Message saveNewMessage(MessageCreateRequestDTO dto);

    /**
     * Update message body and create new idempotency key, if required, then return short response for idempotency key
     *
     * @param idempotencyKey unique key from client
     * @param dto details of the new message
     * @return response stored in the idempotency key
     */
    String updateMessageBody(String idempotencyKey, MessageUpdateRequestDTO dto);

    List<Message> getAllCreatedAfter(String createdAfterDate);
}
