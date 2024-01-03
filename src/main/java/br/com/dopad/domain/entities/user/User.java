package br.com.dopad.domain.entities.user;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {
    private UUID userId;
    private String name;
    private String password;

    public User(UUID userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }
}
