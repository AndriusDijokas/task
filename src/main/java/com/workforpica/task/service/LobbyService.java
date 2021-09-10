package com.workforpica.task.service;

import com.workforpica.task.entity.Lobby;
import com.workforpica.task.entity.Task;
import com.workforpica.task.repository.LobbyRepository;
import com.workforpica.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LobbyService {

    @Autowired
    LobbyRepository lobbyRepository;
    @Autowired
    TaskRepository taskRepository;

    public List<Task> getLobbyTasks(Integer lobbyId){
        return new ArrayList<>();
        
    }
}
