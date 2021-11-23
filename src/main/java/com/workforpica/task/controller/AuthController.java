package com.workforpica.task.controller;

import com.workforpica.task.controller.payload.auth.AuthResponse;
import com.workforpica.task.controller.payload.auth.LoginRequest;
import com.workforpica.task.controller.payload.auth.SignUpRequest;
import com.workforpica.task.controller.payload.lobby.GenericResponse;
import com.workforpica.task.repository.UserRepository;
import com.workforpica.task.security.TokenProvider;
import com.workforpica.task.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("auth/")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final IUserService iUserService;

    private final TokenProvider tokenProvider;

    public AuthController(AuthenticationManager authenticationManager, IUserService iUserService, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.iUserService = iUserService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("signup")
    public ResponseEntity<GenericResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        Long userId =iUserService.registerUser(signUpRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/me")
                .buildAndExpand(userId).toUri();

        return ResponseEntity.created(location)
                .body(new GenericResponse(HttpStatus.CREATED.name(), "User was created"));
    }
}
