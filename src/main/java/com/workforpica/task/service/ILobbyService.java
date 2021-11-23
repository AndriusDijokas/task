package com.workforpica.task.service;

import com.workforpica.task.entity.Lobby;
import com.workforpica.task.entity.User;

public interface ILobbyService {
     void addUserToLobby(String QRCode, Long lobbyId, User user);
     void createLobby(User user,String name);
     void createCategoryForLobby(String categoryName, Long lobbyId);
     void createTaskInLobbyForCategory(Long categoryId, Long lobbyId, String text, Long userId);

}
