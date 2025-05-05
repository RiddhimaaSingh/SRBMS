package service;

import entity.*;
import repo.UserRepo;
import util.InputValidator;

public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean registerUser(String userId, String name, String email, String password, String role) {
        if (!InputValidator.isValidEmail(email) || !InputValidator.isValidPassword(password)) {
            return false;
        }
        if (userRepo.findByEmail(email) != null) {
            return false;
        }

        User user;
        switch (role.toLowerCase()) {
            case "admin" -> user = new Admin(userId, name, email, password);
            case "manager" -> user = new ResourceManager(userId, name, email, password);
            case "user" -> user = new RegularUser(userId, name, email, password);
            default -> {
                return false;
            }
        }

        userRepo.save(user);
        return true;
    }

    public User login(String email, String password) {
        User user = userRepo.findByEmail(email);
        return (user != null && user.getPassword().equals(password)) ? user : null;
    }

    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }
}