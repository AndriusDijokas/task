package com.workforpica.task.repository;

import com.workforpica.task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getById(int id);
}
