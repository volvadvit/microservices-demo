package com.volvadvit.springdata.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.volvadvit.springdata.model.dto.request.MessageCreateRequestDTO;
import com.volvadvit.springdata.model.entity.Message;
import com.volvadvit.springdata.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerListener {

    private final MessageService messageService;
    private final ObjectMapper objectMapper;


    @KafkaListener(
            topics = "messages",
            groupId = "messages_consumer",
            // Use custom factory to enable concurrency
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(@Header("event-id") final String eventId,
                       @Header("message-key") final String key,
                       @Payload final String payload,
                       final Acknowledgment acknowledgment
    ) {
        log.info("Received message - eventId: {} - key: {} - payload: {} - thread: {}", eventId, key, payload, Thread.currentThread().getName());

        try {
            final MessageCreateRequestDTO dto = objectMapper.readValue(payload, MessageCreateRequestDTO.class);
            final Message savedMessage = messageService.saveNewMessage(dto);
            // Acknowledge the message after processing
            acknowledgment.acknowledge();
            log.info("New message was created = [{}]", savedMessage.getId());
        } catch (Exception e) {
            log.error("Error processing message: {}", e.getMessage());
        }
    }
}
