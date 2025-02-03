package com.volvadvit.kafkaproducer.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class KafkaProducerConfiguration {

    private static final String TRANSACTION_ID_CONFIG_VALUE = "transaction-id";

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaTransactionManager<String, String> kafkaTransactionManager(final ProducerFactory<String, String> producerFactoryTransactional) {
        return new KafkaTransactionManager<>(producerFactoryTransactional);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplateTransactional(final ProducerFactory<String, String> producerFactoryTransactional) {
        return new KafkaTemplate<>(producerFactoryTransactional);
    }

    @Bean
    public ProducerFactory<String, String> producerFactoryTransactional() {
        final Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // For exactly-once
        config.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, TRANSACTION_ID_CONFIG_VALUE);
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        // Copy of "spring.kafka.producer.acks". Set "all" for exactly-once processing
        config.put(ProducerConfig.ACKS_CONFIG, "all");
        return new DefaultKafkaProducerFactory<>(config);
    }
}
