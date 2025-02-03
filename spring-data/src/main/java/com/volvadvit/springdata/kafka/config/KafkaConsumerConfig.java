package com.volvadvit.springdata.kafka.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private static final String DLT_TOPIC_SUFFIX = ".dlt";

    private final ProducerFactory<Object, Object> producerFactory;

    @Bean
    public KafkaTemplate<Object, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory);
    }

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> consumerConfig() {
        final Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "messages_consumer");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "30000");
        // copy of "spring.kafka.consumer.enable-auto-commit"
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        config.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        return config;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory(
            final ConsumerFactory<String, String> consumerFactory,
            final DefaultErrorHandler errorHandler)
    {
        final ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        // Copy of "spring.kafka.listener.ack-mode". Commit the offset after each record is processed by the listener.
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        factory.setCommonErrorHandler(errorHandler);
        // 2 threads = 2 partitions
        factory.setConcurrency(2);
        return factory;
    }

    /**
     * Publish to dead-letter topic
     */
    @Bean
    public DeadLetterPublishingRecoverer publisher(final KafkaTemplate<Object, Object> bytesTemplate) {
        return new DeadLetterPublishingRecoverer(bytesTemplate, (consumerRecord, exception) ->
                new TopicPartition(consumerRecord.topic() + DLT_TOPIC_SUFFIX, consumerRecord.partition()));
    }

    /**
     * Default exception handler for messages
     */
    @Bean
    public DefaultErrorHandler errorHandler(final DeadLetterPublishingRecoverer deadLetterPublishingRecoverer) {
        final var handler = new DefaultErrorHandler(deadLetterPublishingRecoverer);
        // Handle all exceptions and send to DLT
        handler.addNotRetryableExceptions(Exception.class);
        return handler;
    }
}
