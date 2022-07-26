package com.fjanalytiks.resource;

import com.fjanalytiks.model.User;

import com.fjanalytiks.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/users")
public class UserResource {
    //set logger
    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);
    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    // Get a list of users
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<User> users = userService.getUsers();
        return Response.ok(users).build();
    }

    // Save user
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUser(User user) {
        userService.saveUser(user);
        return Response.ok("User saved successfully").build();
    }
}
