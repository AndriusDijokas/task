package com.workforpica.task.controller.payload.lobby;

import com.workforpica.task.controller.validation.annotation.ValidId;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class CreateLobbyTask {

    @ValidId(what = "CategoryId", message = "Invalid '${validatedValue}' category id")
    Long categoryId;
    @ValidId(what = "LobbyId", message = "Invalid '${validatedValue}' lobby id")
    Long lobbyId;
    @NotNull
    @Size(max=1000, message = "max '${max}' symbols for task description")
    String text;
    @ValidId(message = "Invalid '${validatedValue}' user id")
    Long userId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(Long lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
