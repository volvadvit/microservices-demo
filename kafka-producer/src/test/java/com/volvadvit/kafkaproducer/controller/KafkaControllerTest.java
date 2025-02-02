package com.volvadvit.kafkaproducer.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:29092", "port=29092" })
class KafkaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test user", authorities = {"SCOPE_PUBLIC_API"})
    void sendMessageToKafka_withValidMessage_WithEmbeddedKafka_ExpectedSuccess() throws Exception {
        // Given
        var requestBuilder = MockMvcRequestBuilders.post("/kafka/send").contentType(APPLICATION_JSON)
                .content("""
                        {
                            "message": "test-test",
                            "senderId": 1,
                            "conversationId": 2
                        }
                        """);
        // When
        this.mockMvc.perform(requestBuilder)
                // Then
                .andDo(print())
                .andExpectAll(
                        status().isOk()
                );
    }
}