package br.com.dopad.domain.entities.user;

import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String password;

    public User(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(UUID id) {
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(UUID id, String username) {
        this.id = id;
        this.username = username;
    }

    public static User createWithOnlyId(UUID userId) {
        return new User(userId);
    }

    public static User createWithOnlyNameAndPassword(String username, String password) {
        return new User(username, password);
    }

    public static User createWithOnlyIdAndUsername(UUID id, String username) {
        return new User(id, username);
    }

    public User getNewInstanceWithId(UUID userId) {
        return new User(userId, username, password);
    }

    public static User createFull(UUID userId, String username, String password) {
        return new User(userId, username, password);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
