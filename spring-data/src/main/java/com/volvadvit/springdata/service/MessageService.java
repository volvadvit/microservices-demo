package com.volvadvit.springdata.service;

import com.volvadvit.springdata.entity.Message;

import java.util.Date;
import java.util.List;

public interface MessageService {

    Message saveNewMessage(String message, Integer conversationId, Integer senderId);

    List<Message> getAllCreatedAfter(String createdAfterDate);
}
