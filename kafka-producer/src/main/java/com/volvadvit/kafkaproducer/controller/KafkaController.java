package com.volvadvit.kafkaproducer.controller;

import com.volvadvit.kafkaproducer.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@Slf4j
@RequiredArgsConstructor
public class KafkaController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    private final KafkaProducerService kafkaProducerService;

    /**
     * To call through Gateway use <a href="http://localhost:8765/kafka/send">http://localhost:8765/kafka/send</a>
     */
    @PostMapping("/send")
    public ResponseEntity<Void> sendMessageToKafka(@RequestBody final String message) {
        log.info("Get POST request to [{}] with message [{}]", instanceId, message);
        kafkaProducerService.sendMessage(instanceId, message);
        return ResponseEntity.ok().build();
    }
}
