package br.com.dopad.usecases.user;

import br.com.dopad.domain.entities.user.User;
import br.com.dopad.web.model.user.request.UserRequest;

import java.util.*;

public interface UserCRUD {
    User addUser(UserRequest request);
    User getById(UUID userId);
}
