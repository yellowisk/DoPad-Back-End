package br.com.dopad.usecases.user.gateway;

import br.com.dopad.domain.entities.user.User;

import java.util.*;

public interface UserDAO {
    User saveUser(User user);
    User findUserById(UUID id);
}
