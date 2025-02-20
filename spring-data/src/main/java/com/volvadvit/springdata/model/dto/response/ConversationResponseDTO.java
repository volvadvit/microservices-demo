package com.volvadvit.springdata.model.dto.response;

import java.time.ZonedDateTime;
import java.util.Set;

public record ConversationResponseDTO(
    Integer id,
    String name,
    Set<Integer> usersIDs,
    Set<Integer> messagesIDs,
    ZonedDateTime createdAt) {
}
