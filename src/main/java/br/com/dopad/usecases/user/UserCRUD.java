package br.com.dopad.usecases.user;

import br.com.dopad.domain.entities.user.User;

import java.util.*;

public interface UserCRUD {
    User addUser(String name, String email, String password);
    User getById(UUID userId);
}
