package com.workforpica.task.controller;

import com.workforpica.task.controller.payload.auth.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.websocket.server.PathParam;

@RestController
public class RedirectController {

    @GetMapping("/oauth2/redirect/google")
    public ResponseEntity<TokenResponse> redirectGoogle(@PathParam("token") String token){
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
