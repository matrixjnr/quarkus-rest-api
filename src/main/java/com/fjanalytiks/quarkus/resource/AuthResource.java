package com.fjanalytiks.quarkus.resource;

import com.fjanalytiks.quarkus.model.AuthRequest;
import com.fjanalytiks.quarkus.model.AuthResponse;
import com.fjanalytiks.quarkus.model.User;
import com.fjanalytiks.quarkus.service.TokenService;
import com.fjanalytiks.quarkus.service.UserService;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/v1/auth")
public class AuthResource {

    private final UserService userService;
    private final TokenService tokenService;

    public AuthResource(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    // Save user
    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUser(User user) {
        userService.saveUser(user);
        return Response.ok("User saved successfully").build();
    }

    // login user
    @POST
    @Path("/login")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(AuthRequest authRequest) {
        AuthResponse authResponse = tokenService.generate(authRequest.getEmail(), authRequest.getPassword());
        return Response.ok(authResponse).build();
    }
}
