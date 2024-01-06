package br.com.dopad.web.model.user.response;

import br.com.dopad.domain.entities.user.User;

import java.util.UUID;

public class UserResponse {
    private UUID id;
    private String username;

    public UserResponse(UUID id, String username) {
        this.id = id;
        this.username = username;
    }

    public static UserResponse fromUser(User user) {
        return new UserResponse(user.getId(), user.getUsername());
    }

    public UserResponse() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
