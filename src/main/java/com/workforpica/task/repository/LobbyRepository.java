package com.workforpica.task.repository;

import com.workforpica.task.entity.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface LobbyRepository extends JpaRepository<Lobby, Integer> {
    Lobby getById(Long id);
    boolean existsById(Long id);
}
