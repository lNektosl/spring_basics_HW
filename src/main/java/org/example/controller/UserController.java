package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

//Посредник между пользователем и сервисом
@Component
public class UserController {
    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * 1 - findById
     * 2 - findByEmail
     * 3 - findAll
     * 4 - add
     */
    public void action() {
        boolean stop = false;
        while(!stop) {
            System.out.println("Input your command: ");
            int command = scanner.nextInt();
            scanner.nextLine();

            if (command == 1) {
                findByIdHandler();
            }
            else if (command == 2) {
                findByEmailHandler();
            }
            else if(command == 3) {
                findAllHandler();
            }
            else if(command == 4) {
                addUserHandler();
            }
            else if(command == 5) {
                stop = true;
            }
        }
    }

    private void findByIdHandler() {
        System.out.println("Input user id: ");
        int id = scanner.nextInt();
        System.out.println(userService.findById(id));
    }

    private void findByEmailHandler() {
        System.out.println("Input user email: ");
        String email = scanner.nextLine();
        System.out.println(userService.findByEmail(email));
    }

    private void findAllHandler() {
        userService.findAll().forEach(System.out::println);
    }

    private void addUserHandler() {
        System.out.println("Input your email and password: ");
        String email = scanner.nextLine();

        String password = scanner.nextLine();

        int id = userService.findAll()
                .stream()
                .mapToInt(User::getId)
                .max().getAsInt() + 1;

        User user = User.builder()
                .id(id)
                .email(email)
                .password(password)
                .registrationDate(new Date())
            .build();

        userService.add(user);
    }
}
