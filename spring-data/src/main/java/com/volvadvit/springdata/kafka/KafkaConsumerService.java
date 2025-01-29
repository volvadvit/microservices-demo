package com.volvadvit.springdata.kafka;

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

    @KafkaListener(topics = "messages", groupId="messages_consumer")
    public void listen(final ConsumerRecord<String, MessageRequestDTO> newRecord) {
        log.info("Message from Kafka: key=[{}], value=[{}]", newRecord.key(), newRecord.value().toString());
        //TODO add deserializer
        final Message savedMessage = messageService.saveNewMessage(newRecord.value());
        log.info("New message was created = [{}]", savedMessage.getId());
    }
}
