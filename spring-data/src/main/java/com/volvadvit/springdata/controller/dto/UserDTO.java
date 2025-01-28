package com.volvadvit.springdata.controller.dto;

import java.util.Set;

public class UserDTO {

    public UserDTO(long id, String name, Set<Integer> messagesIDs, Set<Integer> conversationsIDs) {
        this.id = id;
        this.name = name;
        this.messagesIDs = messagesIDs;
        this.conversationsIDs = conversationsIDs;
    }

    private long id;
    private String name;
    private Set<Integer> messagesIDs;
    private Set<Integer> conversationsIDs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Integer> getMessagesIDs() {
        return messagesIDs;
    }

    public void setMessagesIDs(Set<Integer> messagesIDs) {
        this.messagesIDs = messagesIDs;
    }

    public Set<Integer> getConversationsIDs() {
        return conversationsIDs;
    }

    public void setConversationsIDs(Set<Integer> conversationsIDs) {
        this.conversationsIDs = conversationsIDs;
    }
}
