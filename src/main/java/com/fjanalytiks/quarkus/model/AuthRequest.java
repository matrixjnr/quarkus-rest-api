package com.fjanalytiks.quarkus.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@RegisterForReflection
@Getter
@Setter
public class AuthRequest {
    private String email;
    private String password;

    public AuthRequest(){

    }

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
