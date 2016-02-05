package com.toncent.security.repository;

import com.toncent.security.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * AUTHOR: 819521
 * DATE  : 2016/2/4
 * TIME  : 11:27
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    void deleteByIdIn(Long[] ids);
}
