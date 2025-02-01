package com.volvadvit.kafkaproducer.kafka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private static final String MESSAGES_TOPIC_NAME = "messages";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(final String instanceId, final String message) {
        //TODO check different partitions
        kafkaTemplate.send(MESSAGES_TOPIC_NAME, instanceId, message);
    }
}
