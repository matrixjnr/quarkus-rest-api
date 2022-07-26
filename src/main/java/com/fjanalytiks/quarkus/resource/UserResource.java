package com.fjanalytiks.quarkus.resource;

import com.fjanalytiks.quarkus.model.User;

import com.fjanalytiks.quarkus.service.UserService;
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

    //get one user by id
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") String id){
        return Response.ok(userService.findUserById(id)).build();
    }

    @GET
    @Path("/email/{email}")
    public Response getUserByEmail(@PathParam("email") String email){
        return Response.ok(userService.findUserByEmail(email)).build();
    }
}
