package com.volvadvit.springdata.service.impl;

import com.volvadvit.springdata.entity.Conversation;
import com.volvadvit.springdata.repository.ConversationRepository;
import com.volvadvit.springdata.service.ConversationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;

    public ConversationServiceImpl(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    public Conversation getConversationById(final Integer id) {
        return conversationRepository.findById(id).orElseThrow(() ->
                new RuntimeException(String.format("Conversation %s Not Found!", id)));
    }

    @Override
    @Transactional
    public Conversation updateConversationName(final Integer id, final String newName) {
        final Conversation conversation = this.getConversationById(id);
        conversation.setName(newName);
        // No need to call save() method since we use @Transactional
        return conversation;
    }
}

