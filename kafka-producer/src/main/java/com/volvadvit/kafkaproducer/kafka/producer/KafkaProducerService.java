package com.volvadvit.kafkaproducer.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerService {

    @Value("${kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplateTransactional;

    public void sendMessage(final String messageKey, final String messagePayload) {
        try {
            final ProducerRecord<String, String> recordToSend = new ProducerRecord<>(topicName, messageKey, messagePayload);
            recordToSend.headers().add("content-type", "application/json".getBytes());
            recordToSend.headers().add("message-key", messageKey.getBytes());
            recordToSend.headers().add("event-id", UUID.randomUUID().toString().getBytes());

            this.kafkaTemplateTransactional.executeInTransaction(ops -> {
                final CompletableFuture<SendResult<String, String>> future = ops.send(recordToSend);
                return future.handle((result, ex) -> {
                    if (ex != null) {
                        log.error("Non retriable exception found in send operation: {}", ex.getClass().getName());
                    } else {
                        final RecordMetadata metadata = result.getRecordMetadata();
                        log.info("Send result: key={}, value={}; Metadata: topic={}, partition={}, offset={}",
                                messageKey, messagePayload, metadata.topic(), metadata.partition(), metadata.offset());
                    }
                    return result;
                });
            });
        } catch (IllegalStateException e) {
            log.error("Message not sent", e);
        }
    }
}
