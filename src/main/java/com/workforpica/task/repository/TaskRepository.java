package com.workforpica.task.repository;

import com.workforpica.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task getById(Integer id);
}
