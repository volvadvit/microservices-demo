package com.volvadvit.springdata.controller.dto.request;

public class MessageRequestDTO {

    public MessageRequestDTO(String message, int senderId, int conversationId) {
        this.message = message;
        this.senderId = senderId;
        this.conversationId = conversationId;
    }

    private String message;
    private int senderId;
    private int conversationId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }
}
