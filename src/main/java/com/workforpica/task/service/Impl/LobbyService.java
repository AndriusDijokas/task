package com.workforpica.task.service.Impl;

import com.workforpica.task.entity.*;
import com.workforpica.task.repository.*;
import com.workforpica.task.service.ICategoryService;
import com.workforpica.task.service.ILobbyService;
import com.workforpica.task.service.ITaskService;
import com.workforpica.task.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LobbyService implements ILobbyService {

    final
    LobbyRepository lobbyRepository;
    final
    UserLobbyRepository userLobbyRepository;
    final
    ITaskService taskService;
    final
    ICategoryService categoryService;
    final
    IUserService iUserService;

    public LobbyService(LobbyRepository lobbyRepository, UserLobbyRepository userLobbyRepository, UserRepository userRepository, ITaskService taskService, ICategoryService categoryService, IUserService iUserService) {
        this.lobbyRepository = lobbyRepository;
        this.userLobbyRepository = userLobbyRepository;
        this.taskService = taskService;
        this.categoryService = categoryService;
        this.iUserService = iUserService;
    }

    public List<Lobby> getUserLobbies(Long userId) {
        List<Lobby> lobbies = new ArrayList<>();
        User user = iUserService.getUserEntityById(userId);
        user.getUserLobbies().forEach(userLobby -> {
            lobbies.add(userLobby.getLobby());
        });
        return lobbies;
    }

    public void addUserToLobby(String QRCode, Long lobbyId, User user) {
        /*Todo: Possible list because manyToMany*/
        UserLobby userLobby = userLobbyRepository.getUserLobbyByLobbyId(lobbyId);
        if (userLobby.getLobbyQRCode().equalsIgnoreCase(QRCode)) {
            UserLobby userLobbyToAdd = new UserLobby();
            userLobbyToAdd.setUser(user);
            userLobbyToAdd.setLobby(userLobby.getLobby());
            userLobbyToAdd.setGuest(true);
            userLobbyToAdd.setOwner(false);
            userLobbyRepository.save(userLobbyToAdd);
        }
    }

    public void createCategoryForLobby(String categoryName, Long lobbyId) {
        Lobby lobby = lobbyRepository.getById(lobbyId);
        categoryService.createCategory(categoryName, lobby);
    }

    public void createLobby(User user, String name) {
        Lobby lobby = new Lobby();
        lobby.setName(name);

        UserLobby userLobby = new UserLobby();
        userLobby.setLobby(lobby);
        userLobby.setUser(user);
        userLobby.setOwner(true);
        userLobby.setGuest(false);
        userLobby.setLobbyQRCode(user.getName() + user.getId() + "_" + UUID.randomUUID());

        List<UserLobby> userLobbies = new ArrayList<>();
        userLobbies.add(userLobby);
        lobby.setUserLobbies(userLobbies);
        lobbyRepository.save(lobby);

    }

    public void createTaskInLobbyForCategory(Long categoryId, Long lobbyId, String text, Long userId) {
        Category category = categoryService.getById(categoryId);
        Lobby lobby = lobbyRepository.getById(lobbyId);
        User user = iUserService.getUserEntityById(userId);
        taskService.createTask(lobby, category, user, text);
    }

}
