package com.volvadvit.springdata.repository;

import com.volvadvit.springdata.model.entity.Conversation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends CrudRepository<Conversation, Integer> {
}
