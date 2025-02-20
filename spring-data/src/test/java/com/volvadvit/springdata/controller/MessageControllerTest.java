package com.volvadvit.springdata.controller;

import com.volvadvit.springdata.model.dto.request.MessageCreateRequestDTO;
import com.volvadvit.springdata.model.dto.response.MessageResponseDTO;
import com.volvadvit.springdata.model.entity.Conversation;
import com.volvadvit.springdata.model.entity.Message;
import com.volvadvit.springdata.model.entity.User;
import com.volvadvit.springdata.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Модульные тесты для MessageController")
class MessageControllerTest {

    @InjectMocks
    private MessageController messageController;
    @Mock
    private MessageService messageService;
    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        when(bindingResult.hasErrors()).thenReturn(false);
    }

    @Test
    void saveNewMessage_RequestIsValid_SuccessMessageCreationExpected() throws BindException {
        // Given
        final String messageBody = "test message";
        final int messageId = 1;
        final Message newMessage = createNewMessage(messageId, messageBody, 1, 1);
        final MessageCreateRequestDTO requestDTO = new MessageCreateRequestDTO(messageBody, 1, 1);

        when(messageService.saveNewMessage(any())).thenReturn(newMessage);
        // When
        final ResponseEntity<MessageResponseDTO> response = this.messageController.saveNewMessage(requestDTO, bindingResult);
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code should be OK");
        assertNotNull(response.getBody());
        assertEquals(messageBody, response.getBody().body());
        assertEquals(messageId, response.getBody().messageId());
        verify(messageService, times(1)).saveNewMessage(any());
    }

    @Test
    void saveNewMessage_RequestIsNotValid_BindingExceptionExpected() throws BindException {
        // Given
        final String messageBody = "";
        final MessageCreateRequestDTO requestDTO = new MessageCreateRequestDTO(messageBody, 1, 1);
        when(bindingResult.hasErrors()).thenReturn(true);
        // When
        try {
            this.messageController.saveNewMessage(requestDTO, bindingResult);
            fail("BindException expected here");
        } catch (BindException e) {
            // Then
            assertNotNull(e);
        }
    }

    private Message createNewMessage(final int messageId, final String body, final int senderId, final int conversationId) {
        final User testUser = new User(senderId, null, null, null);
        final Conversation testConversation = new Conversation(conversationId, null, null, null, null);
        return new Message(messageId, body, testUser, ZonedDateTime.now(), testConversation);
    }
}