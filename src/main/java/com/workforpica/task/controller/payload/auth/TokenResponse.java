package com.workforpica.task.controller.payload.auth;

public class TokenResponse {
    String token;

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
