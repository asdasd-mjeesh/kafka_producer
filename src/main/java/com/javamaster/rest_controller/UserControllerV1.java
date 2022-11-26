package com.javamaster.rest_controller;

import com.javamaster.model.User;
import com.javamaster.service.UserServerCommunicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserControllerV1 {
    private final UserServerCommunicator userServerCommunicator;

    @Autowired
    public UserControllerV1(UserServerCommunicator userServerCommunicator) {
        this.userServerCommunicator = userServerCommunicator;
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        var createdUser = userServerCommunicator.createUser(user);
        if (createdUser.isPresent()) {
            return new ResponseEntity<>(createdUser.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>("creation failed", HttpStatus.CONFLICT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        var user = userServerCommunicator.getById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(String.format("user with id=%s is not presented", id),
                HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        var users = userServerCommunicator.getAllUsers();
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>("database doesn't contain users",
                HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        boolean deleted = userServerCommunicator.deleteUserById(id);
        if (deleted) {
            return new ResponseEntity<>(String.format("user with id=%s was deleted", id) ,
                    HttpStatus.OK);
        }
        return new  ResponseEntity<>(String.format("user with id=%s wasn't deleted", id),
                HttpStatus.CONFLICT);
    }
}
