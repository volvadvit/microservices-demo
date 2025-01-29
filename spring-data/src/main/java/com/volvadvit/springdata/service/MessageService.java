package com.volvadvit.springdata.service;

import com.volvadvit.springdata.dto.request.MessageRequestDTO;
import com.volvadvit.springdata.entity.Message;

import java.util.List;

public interface MessageService {

    Message saveNewMessage(MessageRequestDTO dto);

    List<Message> getAllCreatedAfter(String createdAfterDate);
}
