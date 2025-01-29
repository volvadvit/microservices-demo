package com.volvadvit.springdata.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ConversationResponseDTO {

    public ConversationResponseDTO(Integer id, String name, Set<Integer> users, Set<Integer> messages) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.messages = messages;
    }

    private Integer id;
    private String name;
    private Set<Integer> users;
    private Set<Integer> messages;
}
