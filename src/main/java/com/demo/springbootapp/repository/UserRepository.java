package com.demo.springbootapp.repository;

import com.demo.springbootapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends MongoRepository<User, String> {
    List<User> findByName(String name);
}
