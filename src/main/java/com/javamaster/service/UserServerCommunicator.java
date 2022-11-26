package com.javamaster.service;

import com.javamaster.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServerCommunicator {

    Optional<User> createUser(User user);

    Optional<User> getById(Long id);

    List<User> getAllUsers();

    boolean deleteUserById(Long id);
}
