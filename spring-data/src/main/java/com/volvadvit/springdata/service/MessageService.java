package com.volvadvit.springdata.service;

import com.volvadvit.springdata.model.dto.request.MessageCreateRequestDTO;
import com.volvadvit.springdata.model.dto.request.MessageUpdateRequestDTO;
import com.volvadvit.springdata.model.entity.Message;

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

    /**
     * Returns all messages created after provided time
     *
     * @param createdAfterDate format: 2011-12-03T10:15:30+01:00[Europe/ Paris]
     * @return messages
     */
    List<Message> getAllCreatedAfter(String createdAfterDate);
}
