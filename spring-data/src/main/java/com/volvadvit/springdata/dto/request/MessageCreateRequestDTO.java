package com.volvadvit.springdata.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessageCreateRequestDTO {

    @NotNull
    @Size(min = 1, max = 2048)
    private String message;
    @NotNull
    private Integer senderId;
    @NotNull
    private Integer conversationId;
}
