package com.volvadvit.springdata.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageUpdateRequestDTO {

    @NotNull
    @Size(min = 1, max = 2048)
    private String message;
    @NotNull
    private Integer messageId;
}
