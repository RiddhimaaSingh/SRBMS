package com.srbms.controller;

import com.srbms.entity.User;
import com.srbms.repo.UserRepo;
import com.srbms.service.UserService;

import java.util.Scanner;
import java.util.UUID;

public class UserController {
    private final UserService userService;
    private final Scanner scanner;

    public UserController(UserRepo userRepo) {
        this.userService = new UserService(userRepo);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n--- SRBMS ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> handleRegistration();
                case 2 -> handleLogin();
                case 3 -> {
                    System.out.println("Exiting SRBMS...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void handleRegistration() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter role (admin/manager/user): ");
        String role = scanner.nextLine();

        String userId = UUID.randomUUID().toString();

        boolean success = userService.registerUser(userId, name, email, password, role);
        if (success) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed! Check email/password format or if user already exists.");
        }
    }

    private void handleLogin() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userService.login(email, password);
        if (user != null) {
            System.out.println("Login successful! Welcome " + user.getName());
            user.showMenu(); // Dynamic menu based on role
        } else {
            System.out.println("Invalid email or password.");
        }
    }
}
