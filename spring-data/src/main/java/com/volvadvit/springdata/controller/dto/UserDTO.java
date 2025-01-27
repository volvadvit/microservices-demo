package com.volvadvit.springdata.controller.dto;

import java.util.Set;

public class UserDTO {

    public UserDTO(long id, String name, Set<Long> messagesIDs, Set<Long> conversationsIDs) {
        this.id = id;
        this.name = name;
        this.messagesIDs = messagesIDs;
        this.conversationsIDs = conversationsIDs;
    }

    public long id;
    public String name;
    public Set<Long> messagesIDs;
    public Set<Long> conversationsIDs;
}
