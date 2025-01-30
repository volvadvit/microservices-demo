package com.volvadvit.springdata.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volvadvit.springdata.dto.request.MessageRequestDTO;
import com.volvadvit.springdata.entity.Message;
import com.volvadvit.springdata.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final MessageService messageService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "messages", groupId="messages_consumer")
    public void listen(final ConsumerRecord<String, String> newRecord) {
        try {
            log.info("Message from Kafka: key=[{}], value=[{}]", newRecord.key(), newRecord.value());
            final MessageRequestDTO dto = objectMapper.readValue(newRecord.value(), MessageRequestDTO.class);
            //TODO add deserializer
            final Message savedMessage = messageService.saveNewMessage(dto);
            log.info("New message was created = [{}]", savedMessage.getId());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
