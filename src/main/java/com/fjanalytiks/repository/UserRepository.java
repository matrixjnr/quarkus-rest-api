package com.fjanalytiks.repository;

import com.fjanalytiks.model.User;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.inject.Singleton;

public interface UserRepository extends PanacheMongoRepository<User> {

}
