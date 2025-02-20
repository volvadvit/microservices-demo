package com.volvadvit.springdata.repository;

import com.volvadvit.springdata.model.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {
}
