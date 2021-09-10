package com.workforpica.task.repository;

import com.workforpica.task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigInteger;

public interface UserRepository extends JpaRepository<User, BigInteger> {

}
