package com.volvadvit.springdata.controller.dto.response;

import java.util.Set;

public class UserResponseDTO {

    public UserResponseDTO(int id, String name, Set<Long> messagesIDs, Set<Long> conversationsIDs) {
        this.id = id;
        this.name = name;
        this.messagesIDs = messagesIDs;
        this.conversationsIDs = conversationsIDs;
    }

    private int id;
    private String name;
    private Set<Long> messagesIDs;
    private Set<Long> conversationsIDs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getMessagesIDs() {
        return messagesIDs;
    }

    public void setMessagesIDs(Set<Long> messagesIDs) {
        this.messagesIDs = messagesIDs;
    }

    public Set<Long> getConversationsIDs() {
        return conversationsIDs;
    }

    public void setConversationsIDs(Set<Long> conversationsIDs) {
        this.conversationsIDs = conversationsIDs;
    }
}
