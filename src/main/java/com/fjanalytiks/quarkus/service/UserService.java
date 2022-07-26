package com.fjanalytiks.quarkus.service;

import com.fjanalytiks.quarkus.model.User;
import com.fjanalytiks.quarkus.repository.UserRepository;
import com.fjanalytiks.quarkus.resource.UserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserService implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    // Get a list of users
    public List<User> getUsers() {
        List<User> users = findAll().list();
        LOGGER.info("Returning users in service: {}", users);

        return users;
    }

    //save user
    public void saveUser(User user) {
        LOGGER.info("Saving user in service: {}", user);
        persist(user);
    }
}
