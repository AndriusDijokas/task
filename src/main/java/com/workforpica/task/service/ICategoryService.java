package com.workforpica.task.service;

import com.workforpica.task.entity.Category;
import com.workforpica.task.entity.Lobby;

public interface ICategoryService {
    void createCategory(String name, Lobby lobby);
    Category getById(Long id);
}
