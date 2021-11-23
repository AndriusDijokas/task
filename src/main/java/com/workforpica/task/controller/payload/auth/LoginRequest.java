package com.workforpica.task.controller.payload.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {

    @Email
    @NotBlank
    String email;
    @NotBlank
    @Size(min= 6, max= 15, message = "password must be between {min} and {max} symbols")
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
