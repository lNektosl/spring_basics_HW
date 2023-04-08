package org.example.config;

import org.example.controller.UserController;
import org.example.dao.UserDao;
import org.example.dao.UserDaoImpl;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = "org.example")
public class ApplicationConfig {
    //Внедрить данный бин внутрь UserDaoImpl, чтобы получать данные из бд.
    @Bean
    public DataSource dataSource() {
        //Введите ваши данные для бд
        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setServerName("localhost");
        source.setPortNumber(5432);
        source.setDatabaseName("test_db");
        source.setUser("postgres");
        source.setPassword("123");
        return source;
    }
    @Bean
    public UserDao userDao() throws SQLException {
        return new UserDaoImpl(dataSource());
    }
    @Bean
    public UserService userService() throws SQLException {
        return new UserServiceImpl(userDao());
    }
    @Bean
    public UserController userController() throws SQLException {
        return new UserController(userService());
    }
}
