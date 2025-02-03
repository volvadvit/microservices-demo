package com.volvadvit.springdata.controller;

import com.volvadvit.springdata.dto.request.MessageCreateRequestDTO;
import com.volvadvit.springdata.dto.request.MessageUpdateRequestDTO;
import com.volvadvit.springdata.dto.response.MessageResponseDTO;
import com.volvadvit.springdata.entity.Message;
import com.volvadvit.springdata.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> saveNewMessage(@Valid @RequestBody final MessageCreateRequestDTO requestDTO,
                                                             final BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            final Message newMessage = messageService.saveNewMessage(requestDTO);
            return ResponseEntity.ok(toMessageResponseDTO(newMessage));
        }
    }

    @PutMapping
    public ResponseEntity<String> updateMessageBody(@RequestHeader("Idempotency-Key") String idempotencyKey,
                                                                   @Valid @RequestBody final MessageUpdateRequestDTO requestDTO) {
        final String response = messageService.updateMessageBody(idempotencyKey, requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MessageResponseDTO>> getAllMessagesCreatedAfterDate(@RequestParam final String date) {
        return ResponseEntity.ok(
                Optional.ofNullable(messageService.getAllCreatedAfter(date)).stream()
                        .flatMap(Collection::stream)
                        .map(this::toMessageResponseDTO)
                        .toList());
    }

    private MessageResponseDTO toMessageResponseDTO(final Message message) {
        return new MessageResponseDTO(
                message.getBody(),
                message.getId(),
                message.getSender().getId(),
                message.getConversation().getId());
    }
}
