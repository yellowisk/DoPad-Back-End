package br.com.dopad.usecases.user;

import br.com.dopad.domain.entities.user.User;
import br.com.dopad.usecases.user.gateway.UserDAO;
import br.com.dopad.web.model.user.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserCRUDImpl implements UserCRUD {
    private final UserDAO userDAO;

    public UserCRUDImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User addUser(UserRequest request) {
        User user = request.toUser();
        return userDAO.saveUser(user);
    }

    @Override
    public User getById(UUID userId) {
        return userDAO.findUserById(userId);
    }
}
