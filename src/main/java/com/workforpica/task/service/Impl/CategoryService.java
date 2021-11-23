package com.workforpica.task.service.Impl;

import com.workforpica.task.entity.Category;
import com.workforpica.task.entity.Lobby;
import com.workforpica.task.repository.CategoryRepository;
import com.workforpica.task.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    final
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(String name, Lobby lobby){
        Category category =  new Category();
        category.setName(name);
        category.setLobby(lobby);
        categoryRepository.save(category);
    }

    public Category getById(Long id){
        return categoryRepository.getById(id);
    }
}
