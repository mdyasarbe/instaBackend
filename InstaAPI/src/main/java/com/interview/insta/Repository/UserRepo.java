package com.interview.insta.Repository;

import com.interview.insta.entity.Users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<Users, Integer> {
    Users findByuserName(String username);
}
