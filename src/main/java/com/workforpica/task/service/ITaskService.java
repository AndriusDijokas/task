package com.workforpica.task.service;

import com.workforpica.task.entity.Category;
import com.workforpica.task.entity.Lobby;
import com.workforpica.task.entity.User;

public interface ITaskService {
    void createTask(Lobby lobby, Category category, User user, String text);
}
