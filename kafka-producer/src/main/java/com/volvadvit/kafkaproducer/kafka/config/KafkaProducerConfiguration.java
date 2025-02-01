package com.volvadvit.kafkaproducer.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfiguration {

    @Bean
    public NewTopic messageTopic() {
        return new NewTopic(
                "messages",
                2,
                (short) 1);
    }

    @Bean
    public NewTopic dltTopic() {
        return new NewTopic(
                "messages.dlt",
                2,
                (short) 1);
    }
}
