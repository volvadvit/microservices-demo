package com.volvadvit.springdata.mapper;

import com.volvadvit.springdata.model.dto.response.ConversationResponseDTO;
import com.volvadvit.springdata.model.dto.response.MessageResponseDTO;
import com.volvadvit.springdata.model.dto.response.UserResponseDTO;
import com.volvadvit.springdata.model.entity.Conversation;
import com.volvadvit.springdata.model.entity.Message;
import com.volvadvit.springdata.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConversationDtoMapper {

    @Mapping(source = "messages", target = "messagesIDs", qualifiedByName = "messageEntityListToMessageIdSet")
    @Mapping(source = "users", target = "usersIDs", qualifiedByName = "userEntitySetToUserIdSet")
    ConversationResponseDTO toConversationResponseDTO(Conversation conversation);

    @Named("messageEntityListToMessageIdSet")
    static Set<Integer> messageEntityListToMessageIdSet(List<Message> messages) {
        return messages.stream().map(Message::getId).collect(Collectors.toSet());
    }

    @Named("userEntitySetToUserIdSet")
    static Set<Integer> userEntitySetToUserIdSet(Set<User> users) {
        return users.stream().map(User::getId).collect(Collectors.toSet());
    }
}
