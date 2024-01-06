package br.com.dopad.web.model.user.request;

import br.com.dopad.domain.entities.user.User;

public class UserRequest {
    private String username;
    private String password;

    public UserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User toUser() {
        return new User(username, password);
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
