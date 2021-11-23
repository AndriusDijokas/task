package com.workforpica.task.service;

import com.workforpica.task.entity.User;
import com.workforpica.task.repository.UserRepository;
import com.workforpica.task.service.Impl.LobbyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LobbyServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    LobbyService lobbyService;

    /*Todo: Write good tests...*/
    @Test
    void createLobby() {
        User user = new User();
        user.setEmail("tadi");
        user.setName("Andrius");
        user.setLastName("Dijokas");
        userRepository.save(user);
        lobbyService.createLobby(user,"Home Office");
        System.out.println(user.getLastName());
        User user1 =  userRepository.getById(1L);
        System.out.println(user1.getName());
        user.getUserLobbies().forEach(i -> {System.out.println(i.getCreateDate());});
    }
}