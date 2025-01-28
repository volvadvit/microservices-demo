package com.volvadvit.springdata.controller;

import com.volvadvit.springdata.controller.dto.request.MessageRequestDTO;
import com.volvadvit.springdata.controller.dto.response.MessageResponseDTO;
import com.volvadvit.springdata.entity.Message;
import com.volvadvit.springdata.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> saveNewMessage(@Valid @RequestBody final MessageRequestDTO requestDTO,
                                                             final BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            final Message newMessage = messageService.saveNewMessage(requestDTO.getMessage(), requestDTO.getConversationId(), requestDTO.getSenderId());
            return ResponseEntity.ok(convertMessageToDTO(newMessage));
        }
    }

    @GetMapping
    public ResponseEntity<List<MessageResponseDTO>> getAllMessagesCreatedAfterDate(@RequestParam final String date) {
        return ResponseEntity.ok(
                Optional.ofNullable(messageService.getAllCreatedAfter(date)).stream()
                        .flatMap(Collection::stream)
                        .map(this::convertMessageToDTO)
                        .toList());
    }

    private MessageResponseDTO convertMessageToDTO(final Message message) {
        return new MessageResponseDTO(
                message.getBody(),
                message.getId(),
                message.getSender().getId(),
                message.getConversation().getId());
    }
}
