package com.workforpica.task.controller.payload.lobby;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.workforpica.task.controller.validation.annotation.ValidId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonAutoDetect
public class CreateLobby {

    @ValidId(message = "Invalid '${validatedValue}' user id")
    Long userId;
    @NotNull(message = "lobby name can not be empty")
    @Size(min = 2 , max = 30, message = "lobby can be between {min} and {max} symbols")
    String lobbyName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }
}
