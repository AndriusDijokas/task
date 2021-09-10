package com.workforpica.task.repository;

import com.workforpica.task.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category getById(Integer id);
}
