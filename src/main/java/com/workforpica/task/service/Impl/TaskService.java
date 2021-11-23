package com.workforpica.task.service.Impl;

import com.workforpica.task.entity.Category;
import com.workforpica.task.entity.Lobby;
import com.workforpica.task.entity.Task;
import com.workforpica.task.entity.User;
import com.workforpica.task.repository.TaskRepository;
import com.workforpica.task.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements ITaskService {

    final
    TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(Lobby lobby, Category category, User user, String text){
        Task task = new Task();
        task.setText(text);
        task.setCategory(category);
        task.setLobby(lobby);
        task.setUser(user);
        taskRepository.save(task);
    }

}
