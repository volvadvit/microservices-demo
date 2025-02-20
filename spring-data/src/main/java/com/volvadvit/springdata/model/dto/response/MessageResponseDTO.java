package com.volvadvit.springdata.model.dto.response;

import java.time.ZonedDateTime;

public record MessageResponseDTO(
    String body,
    int messageId,
    int senderId,
    int conversationId,
    ZonedDateTime createdAt) {
}
