package com.volvadvit.springdata.service.impl;

import com.volvadvit.springdata.model.entity.Conversation;
import com.volvadvit.springdata.model.entity.Message;
import com.volvadvit.springdata.model.entity.User;
import com.volvadvit.springdata.service.ConversationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Requires configuration to connect TestContainers with Docker environment
 */
@Transactional
@SpringBootTest
@Sql("/sql/test_conversations.sql")
class ConversationServiceImplTest {

    @Autowired
    private ConversationService conversationService;

    /**
     * Requires connection from Testcontainers to Docker
     */
    @Test
    void getConversationById_WithExistingConversation_Success() {
        // Given
        final String expectedConversationName = "Chat";
        final int expectedConversationId = 1;
        final List<Integer> expectedUsersIDs = List.of(1);
        final List<Integer> expectedMessagesIDs = List.of(1);
        // When
        final Conversation actualConversation = conversationService.getConversationById(1);
        // Then
        assertNotNull(actualConversation);
        assertEquals(expectedConversationId, actualConversation.getId());
        assertEquals(expectedConversationName, actualConversation.getName());
        assertEquals(expectedUsersIDs, actualConversation.getUsers().stream().map(User::getId).toList());
        assertEquals(expectedMessagesIDs, actualConversation.getMessages().stream().map(Message::getId).toList());
    }
}