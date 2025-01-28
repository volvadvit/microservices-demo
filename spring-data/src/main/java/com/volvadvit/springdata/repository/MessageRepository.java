package com.volvadvit.springdata.repository;

import com.volvadvit.springdata.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findAllByCreatedAtAfter(final LocalDateTime createdAfterDate);
}
