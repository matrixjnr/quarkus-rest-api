package com.fjanalytiks.quarkus.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

@RegisterForReflection
@Setter
@Getter
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String expiryDate;
    private User user;

    public AuthResponse(String token, String expiryDate, User user) {
        this.token = token;
        this.expiryDate = expiryDate;
        this.user = user;
    }
}
