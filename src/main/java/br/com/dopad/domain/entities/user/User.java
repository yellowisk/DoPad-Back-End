package br.com.dopad.domain.entities.user;

import java.util.UUID;

public class User {
    private UUID userId;
    private String name;
    private String password;

    public User(UUID userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
