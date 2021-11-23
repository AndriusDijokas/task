package com.workforpica.task.controller;

import com.workforpica.task.model.UserDto;
import com.workforpica.task.security.CurrentUser;
import com.workforpica.task.security.UserPrincipal;
import com.workforpica.task.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users/")
public class UserController {

    final
    IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("me")
    public ResponseEntity<UserDto> getMe(@CurrentUser UserPrincipal userPrincipal){
        return ResponseEntity.ok(iUserService.getUserById(userPrincipal.getId()));
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getMe(@CurrentUser UserPrincipal userPrincipal,
                                         @PathVariable Long userId ){
        return ResponseEntity.ok(iUserService.getUserById(userId));
    }
}
