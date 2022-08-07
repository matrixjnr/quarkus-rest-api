package com.fjanalytiks.quarkus.security;

import com.fjanalytiks.quarkus.model.User;
import com.fjanalytiks.quarkus.service.UserService;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.SecurityContext;


@Singleton
public class SecurityConfig {

    @Inject
    JsonWebToken jwt;

    @Inject
    UserService userService;

    @Inject
    @Claim(standard = Claims.preferred_username)
    String username;

    private User getUsername(SecurityContext ctx) {
        String email;
        if (ctx.getUserPrincipal() == null) {
            email = "anonymous";
        } else if (!ctx.getUserPrincipal().getName().equals(jwt.getName())) {
            throw new InternalServerErrorException("Principal and JsonWebToken emails do not match");
        } else {
            email = ctx.getUserPrincipal().getName();
        }
        return userService.findUserByEmail(email);
    }

    private boolean hasJwt() {
        return jwt.getClaimNames() != null;
    }
}
