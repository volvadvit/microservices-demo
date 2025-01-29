package com.volvadvit.kafkaproducer.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private static final String MESSAGES_TOPIC_NAME = "messages";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(final String message) {
        //TODO check different partitions
        kafkaTemplate.send(MESSAGES_TOPIC_NAME, UUID.randomUUID().toString(), message);
    }
}
