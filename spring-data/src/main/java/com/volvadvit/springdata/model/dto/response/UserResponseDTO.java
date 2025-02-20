package com.volvadvit.springdata.model.dto.response;

import java.util.Set;

public record UserResponseDTO(
    int id,
    String name,
    Set<Integer> messagesIDs,
    Set<Integer> conversationsIDs) {

}
