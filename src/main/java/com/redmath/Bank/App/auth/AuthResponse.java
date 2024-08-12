package com.redmath.Bank.App.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponse {
    private Long id;
    private String role;
    public AuthResponse(String response, Long id) {
        this.id = id;
    }
    public AuthResponse( Long id, String role) {
        this.id = id;
        this.role = role;

    }
}
