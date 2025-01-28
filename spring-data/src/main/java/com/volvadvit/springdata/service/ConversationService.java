package com.volvadvit.springdata.service;

import com.volvadvit.springdata.entity.Conversation;

public interface ConversationService {

    Conversation getConversationById(Integer id);

    Conversation updateConversationName(Integer id, String newName);
}
