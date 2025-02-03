package com.volvadvit.kafkaproducer.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaNewTopicsConfiguration {

    private static final String DLT_NAME_SUFFIX = ".dlt";

    @Value("${kafka.topic.name}")
    private String topicName;

    @Bean
    public NewTopic messageTopic() {
        return new NewTopic(
                topicName,
                2,
                (short) 1);
    }

    @Bean
    public NewTopic dltTopic() {
        return new NewTopic(
                topicName + DLT_NAME_SUFFIX,
                2,
                (short) 1);
    }
}
