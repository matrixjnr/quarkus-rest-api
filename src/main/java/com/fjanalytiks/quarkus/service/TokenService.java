package com.fjanalytiks.quarkus.service;

import com.fjanalytiks.quarkus.model.AuthResponse;
import com.fjanalytiks.quarkus.model.User;
import com.fjanalytiks.quarkus.resource.UserResource;
import com.fjanalytiks.quarkus.util.JwtUtils;
import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.NotFoundException;
import java.time.Clock;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class TokenService {
    private final UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    public TokenService(UserService userService) {
        this.userService = userService;
    }

    public AuthResponse generate(String email, String password) {
        // check if user exists in database
        // if not, throw exception
        // if yes, generate token
        Optional<User> user = Optional.ofNullable(userService.findUserByEmail(email));
        if(user.isPresent()){
            if(!user.get().getPassword().equals(password)) {
                throw new RuntimeException("Invalid Credentials");
            }

            try {
                LOGGER.info("Login in trial setUP");

                JwtClaims jwtClaims = new JwtClaims();
                jwtClaims.setIssuer("https://fjanalytiks.com");
                jwtClaims.setJwtId("a-123");
                jwtClaims.setSubject(email);
                jwtClaims.setClaim(Claims.upn.name(), email);
                jwtClaims.setClaim(Claims.preferred_username.name(), email);
                jwtClaims.setClaim(Claims.groups.name(), List.of(JwtUtils.ROLE_USER));
                jwtClaims.setAudience("using-jwt");
                jwtClaims.setIssuedAt(NumericDate.now());
                jwtClaims.setExpirationTimeMinutesInTheFuture(60);

                String token = JwtUtils.generateTokenString(jwtClaims);

//                LOGGER.info("Token: {}", token);

                AuthResponse authResponse= new AuthResponse();
                authResponse.setToken(token);
                authResponse.setExpiryDate(Clock.systemDefaultZone().instant().plusSeconds(3600).toString());
                authResponse.setUser(user.get());

                return authResponse;
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("Error generating token");
                throw new RuntimeException("Oops!");
            }
        } else {
            throw new NotFoundException("Invalid Credentials");
        }

    }
}
