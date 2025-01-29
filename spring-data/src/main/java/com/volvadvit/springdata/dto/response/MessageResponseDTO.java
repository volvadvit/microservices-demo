package com.volvadvit.springdata.dto.response;

public class MessageResponseDTO {

    public MessageResponseDTO(String message, int messageId, int senderId, int conversationId) {
        this.message = message;
        this.messageId = messageId;
        this.senderId = senderId;
        this.conversationId = conversationId;
    }

    private String message;
    private int messageId;
    private int senderId;
    private int conversationId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
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
