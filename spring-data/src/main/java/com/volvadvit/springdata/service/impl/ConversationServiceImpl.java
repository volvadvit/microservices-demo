package com.volvadvit.springdata.service.impl;

import com.volvadvit.springdata.entity.Conversation;
import com.volvadvit.springdata.repository.ConversationRepository;
import com.volvadvit.springdata.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;

    @Override
    public Conversation getConversationById(final Integer id) {
        return conversationRepository.findById(id).orElse(null);
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

