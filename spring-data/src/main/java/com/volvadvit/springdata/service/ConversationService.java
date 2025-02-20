package com.volvadvit.springdata.service;

import com.volvadvit.springdata.model.entity.Conversation;

public interface ConversationService {

    Conversation getConversationById(Integer id);

    Conversation updateConversationName(Integer id, String newName);
}
