package com.workforpica.task.controller;

import com.workforpica.task.entity.Task;
import com.workforpica.task.service.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("lobby/")
public class LobbyController {


    @Autowired
    LobbyService lobbyService;

    @GetMapping("tasks")
    public List<Task> getLobbyTasks(Integer lobbyId){
        return lobbyService.getLobbyTasks(lobbyId);
    }
}
