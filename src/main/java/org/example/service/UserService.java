package org.example.service;

import org.example.exceptions.EmailAlreadyExistsException;
import org.example.model.User;

import java.util.List;
import java.util.NoSuchElementException;

public interface UserService {
    User findById(int id);
    User findByEmail(String email);
    List<User> findAll();
    void add(User user);
}
