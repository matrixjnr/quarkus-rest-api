package com.fjanalytiks.quarkus.service;

import com.fjanalytiks.quarkus.model.User;
import com.fjanalytiks.quarkus.repository.UserRepository;
import com.fjanalytiks.quarkus.resource.UserResource;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

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

    //find a user based on id
    public User findUserById(String id){
        User user = find("_id", new ObjectId(id)).firstResult();
        LOGGER.info("User object from db: {}", user);
        if(user != null){
            return user;
        }
        LOGGER.info("User with id: {} not found!", id);
        throw new NotFoundException("User with id: "+ id + " not found");
    }

    public User findUserByEmail(String email){
        User user = find("email", email).firstResult();
        LOGGER.info("User object from db: {}", user);
        if(user != null){
            return user;
        }
        LOGGER.info("User x with email: {} not found!", email);
        throw new NotFoundException("User with email: "+ email + " not found");
    }
}
