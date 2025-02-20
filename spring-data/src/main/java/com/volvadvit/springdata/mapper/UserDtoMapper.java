package com.volvadvit.springdata.mapper;

import com.volvadvit.springdata.model.dto.response.UserResponseDTO;
import com.volvadvit.springdata.model.entity.Conversation;
import com.volvadvit.springdata.model.entity.Message;
import com.volvadvit.springdata.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserDtoMapper {

    @Mapping(source = "sentMessages", target = "messagesIDs", qualifiedByName = "messageEntityListToMessageIdSet")
    @Mapping(source = "conversations", target = "conversationsIDs", qualifiedByName = "conversationEntitySetToConversationIdSet")
    UserResponseDTO toUserResponseDTO(User user);

    @Named("messageEntityListToMessageIdSet")
    static Set<Integer> messageEntityListToMessageIdSet(List<Message> sentMessages) {
        return sentMessages.stream().map(Message::getId).collect(Collectors.toSet());
    }

    @Named("conversationEntitySetToConversationIdSet")
    static Set<Integer> conversationEntitySetToConversationIdSet(Set<Conversation> conversations) {
        return conversations.stream().map(Conversation::getId).collect(Collectors.toSet());
    }
}
