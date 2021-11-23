package com.workforpica.task.repository;

import com.workforpica.task.entity.Lobby;
import com.workforpica.task.entity.User;
import com.workforpica.task.entity.UserLobby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLobbyRepository extends JpaRepository<UserLobby, Long> {
    List<UserLobby>  getUserLobbyByLobby(Lobby lobby);
    List<UserLobby> getUserLobbyByUser(User user);
    UserLobby getUserLobbyByLobbyId(Long id);
    boolean existsById(Long id);

}
