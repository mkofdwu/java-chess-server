package com.example.javachessserver.user;

import com.example.javachessserver.user.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
