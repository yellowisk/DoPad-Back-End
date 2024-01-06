package br.com.dopad.web.controller;

import br.com.dopad.domain.entities.user.User;
import br.com.dopad.usecases.user.UserCRUD;
import br.com.dopad.web.model.user.request.UserRequest;
import br.com.dopad.web.model.user.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private UserCRUD userCRUD;

    public UserController(UserCRUD userCRUD) {
        this.userCRUD = userCRUD;
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest request) {
        User user = userCRUD.addUser(request);
        return ResponseEntity.ok(UserResponse.fromUser(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID userId) {
        User user = userCRUD.getById(userId);
        return ResponseEntity.ok(UserResponse.fromUser(user));
    }

}
