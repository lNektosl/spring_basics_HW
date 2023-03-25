package org.example.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

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
}
