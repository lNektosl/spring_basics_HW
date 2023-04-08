package org.example.dao;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {
    private final DataSource source;
    @Autowired
    public UserDaoImpl(DataSource source) throws SQLException {
        this.source = source;
      }
    private final List<User> users = new ArrayList<>();

      public void boot() throws SQLException {

          Connection cnn = source.getConnection();
          Statement st = cnn.createStatement();
          ResultSet set = st.executeQuery("SELECT * FROM users");
          while(set.next()){
              users.add(User.builder()
                      .id(set.getInt("id"))
                      .email(set.getString("email"))
                      .password(set.getString("password"))
                      .registrationDate(set.getDate("regDate"))
                      .build());
          }
      }

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
    public void add(User user) throws SQLException {
        users.add(user);
        Connection cnn = source.getConnection();
        PreparedStatement statement = cnn.prepareStatement("INSERT INTO Users (email,password, regdate) VALUES ( ?, ?,CURRENT_DATE)");
        statement.setString(1,user.getEmail());
        statement.setString(2,user.getPassword());
        statement.execute();
    }
}
