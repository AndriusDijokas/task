package com.workforpica.task.controller;

import com.workforpica.task.controller.payload.lobby.CreateLobby;
import com.workforpica.task.controller.payload.lobby.CreateLobbyTask;
import com.workforpica.task.entity.User;
import com.workforpica.task.controller.payload.lobby.GenericResponse;
import com.workforpica.task.repository.UserRepository;
import com.workforpica.task.security.CurrentUser;
import com.workforpica.task.security.UserPrincipal;
import com.workforpica.task.service.ILobbyService;
import com.workforpica.task.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;


@RestController
@RequestMapping("lobbies/")
public class LobbyController {

    final
    ILobbyService lobbyService;
    final
    IUserService iUserService;

    public LobbyController(ILobbyService lobbyService, IUserService iUserService) {
        this.lobbyService = lobbyService;
        this.iUserService = iUserService;
    }


    @PostMapping("create")
    public ResponseEntity<GenericResponse> createLobby(@RequestBody @Valid CreateLobby createLobby,
                                                       @CurrentUser UserPrincipal userPrincipal) {
        User user = iUserService.getUserEntityById(createLobby.getUserId());
        lobbyService.createLobby(user, createLobby.getLobbyName().toUpperCase(Locale.ROOT));
        return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.name(), GenericResponse.responses.SUCCESS.name()));
    }

    @PostMapping("task/create")
    public ResponseEntity<GenericResponse> createTask(@RequestBody CreateLobbyTask createLobbyTask,
                                                      @CurrentUser UserPrincipal userPrincipal) {

        lobbyService.createTaskInLobbyForCategory(createLobbyTask.getCategoryId(),
                                                  createLobbyTask.getLobbyId(),
                                                  createLobbyTask.getText(),
                                                  createLobbyTask.getUserId());
        return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.name(), GenericResponse.responses.SUCCESS.name()));
    }

}
