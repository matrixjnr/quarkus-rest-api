package com.fjanalytiks.quarkus.resource;

import com.fjanalytiks.quarkus.model.User;

import com.fjanalytiks.quarkus.service.UserService;
import com.fjanalytiks.quarkus.util.JwtUtils;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonString;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.Optional;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/v1/users")
@RequestScoped
public class UserResource {
    private final UserService userService;

    @Context
    SecurityContext securityContext;

//    @Inject
//    JsonWebToken jwt;

//    @Inject
//    @Claim(standard = Claims.email)
//    String email;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    // Get a list of users
    @GET
    @RolesAllowed({JwtUtils.ROLE_USER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<User> users = userService.getUsers();
        return Response.ok(users).build();
    }

    //get one user by id
    @GET
    @RolesAllowed({JwtUtils.ROLE_ADMIN, JwtUtils.ROLE_USER})
    @Path("/{id}")
    public Response getUserById(@PathParam("id") String id){
        return Response.ok(userService.findUserById(id)).build();
    }

    // get user profile based on security context
    @GET
    @RolesAllowed({JwtUtils.ROLE_ADMIN, JwtUtils.ROLE_USER})
    @Path("/profile")
    public Response getUserProfile(){
        return Response.ok(userService.findUserByEmail(securityContext.getUserPrincipal().getName())).build();
    }

    @GET
    @RolesAllowed({JwtUtils.ROLE_ADMIN, JwtUtils.ROLE_USER})
    @Path("/email/{email}")
    public Response getUserByEmail(@PathParam("email") String email){
        return Response.ok(userService.findUserByEmail(email)).build();
    }
}
