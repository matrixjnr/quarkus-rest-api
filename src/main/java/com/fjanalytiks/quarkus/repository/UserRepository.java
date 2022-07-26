package com.fjanalytiks.quarkus.repository;

import com.fjanalytiks.quarkus.model.User;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

public interface UserRepository extends PanacheMongoRepository<User> {

}
