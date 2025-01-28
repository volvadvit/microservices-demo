package com.volvadvit.springdata.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MessageRequestDTO {

    public MessageRequestDTO(String message, int senderId, int conversationId) {
        this.message = message;
        this.senderId = senderId;
        this.conversationId = conversationId;
    }

    @NotNull
    @Size(min = 1, max = 2048)
    private String message;
    @NotNull
    private Integer senderId;
    @NotNull
    private Integer conversationId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }
}
