package org.example.service;

import org.example.dao.UserDao;
import org.example.exceptions.EmailAlreadyExistsException;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Component
public class UserServiceImpl implements UserService {
    private final UserDao dao;

    @Autowired
    public UserServiceImpl(@Qualifier("userDaoImpl") UserDao dao) throws SQLException {
        this.dao = dao;
        dao.boot();
    }

    @Override
    public User findById(int id){
        return dao.findById(id).orElseThrow(() -> new NoSuchElementException(
            String.format("User with id = %d doesn't exist", id))
        );
    }

    @Override
    public User findByEmail(String email) {
        return dao.findByEmail(email).orElseThrow(() -> new NoSuchElementException(
            String.format("User with email = %s doesn't exist", email))
        );
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public void add(User user) throws SQLException {
        Optional<User> existingUser = dao.findByEmail(user.getEmail());
        if(existingUser.isPresent()) {
            throw new EmailAlreadyExistsException(
                String.format("User with email %s already exists", user.getEmail())
            );
        }
        dao.add(user);
    }
}
