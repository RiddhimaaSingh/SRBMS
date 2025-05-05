package com.srbms.service;

import com.srbms.entity.Admin;
import com.srbms.entity.RegularUser;
import com.srbms.entity.ResourceManager;
import com.srbms.entity.User;
import com.srbms.repo.UserRepo;
import com.srbms.util.InputValidator;

public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean registerUser(String userId, String name, String email, String password, String role) {
        if (!InputValidator.isValidEmail(email) || !InputValidator.isValidPassword(password)) {
            return false;
        }
        if (userRepo.existsByEmail(email)) {
            return false;
        }

        User user;
        switch (role.toLowerCase()) {
            case "admin":
                user = new Admin(userId, name, email, password);
                break;
            case "manager":
                user = new ResourceManager(userId, name, email, password);
                break;
            case "user":
                user = new RegularUser(userId, name, email, password);
                break;
            default:
                return false;
        }

        userRepo.saveUser(user);
        return true;
    }

    public User login(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void printAllUsers() {
        for (User user : userRepo.findAllUsers()) {
            System.out.println(user);
        }
    }
}
