package com.volvadvit.springdata.repository;

import com.volvadvit.springdata.model.entity.IdempotencyKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdempotencyKeyRepository extends CrudRepository<IdempotencyKey, String> {

}
