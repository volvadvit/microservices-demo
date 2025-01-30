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

    @KafkaListener(
            topics = "messages",
            groupId="messages_consumer",
            // Use custom factory to enable concurrency
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(final ConsumerRecord<String, String> newRecord) throws JsonProcessingException {
        log.info("Message from Kafka: key=[{}], value=[{}], Thread=[{}}]", newRecord.key(), newRecord.value(), Thread.currentThread().getName());
        final MessageRequestDTO dto = objectMapper.readValue(newRecord.value(), MessageRequestDTO.class);
        //TODO add custom deserializer?
        final Message savedMessage = messageService.saveNewMessage(dto);
        log.info("New message was created = [{}]", savedMessage.getId());
    }
}
