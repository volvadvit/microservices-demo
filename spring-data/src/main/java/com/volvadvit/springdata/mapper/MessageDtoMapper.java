package com.volvadvit.springdata.mapper;

import com.volvadvit.springdata.model.dto.response.MessageResponseDTO;
import com.volvadvit.springdata.model.entity.Conversation;
import com.volvadvit.springdata.model.entity.Message;
import com.volvadvit.springdata.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageDtoMapper {

    @Mapping(source = "sender", target = "senderId", qualifiedByName = "userEntityToUserId")
    @Mapping(source = "conversation", target = "conversationId", qualifiedByName = "conversationEntityToConversationId")
    @Mapping(source = "id", target = "messageId")
    MessageResponseDTO toMessageResponseDTO(Message message);

    @Named("userEntityToUserId")
    static Integer userEntityToUserId(User user) {
        return user.getId();
    }

    @Named("conversationEntityToConversationId")
    static Integer conversationEntityToConversationId(Conversation conversation) {
        return conversation.getId();
    }
}
