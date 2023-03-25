package org.example.dao;

import org.example.exceptions.EmailAlreadyExistsException;
import org.example.model.User;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {
    private final List<User> users = new ArrayList<>(List.of(
            User.builder()
                .id(1)
                .email("i@bikmeev.ru")
                .password("123")
                .registrationDate(new Date())
            .build(),

            User.builder()
                .id(2)
                .email("ivanoff256@mail.ru")
                .password("qwerty123")
                .registrationDate(new Date())
            .build()
        )
    );


    @Override
    public Optional<User> findById(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }
}
