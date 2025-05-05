package repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import entity.User;

public class UserRepository {
    private final Map<String, User> userMap = new HashMap<>(); // key = email or userId

    // Store a user
    public void saveUser(User user) {
        userMap.put(user.getEmail().toLowerCase(), user); // key is email
    }

    // Get user by email (used for login)
    public User findByEmail(String email) {
        return userMap.get(email.toLowerCase());
    }

    // Check if user already exists
    public boolean existsByEmail(String email) {
        return userMap.containsKey(email.toLowerCase());
    }

    // Return all users
    public Collection<User> findAllUsers() {
        return userMap.values();
    }

    // Remove user by email (optional)
    public boolean deleteByEmail(String email) {
        return userMap.remove(email.toLowerCase()) != null;
    }

    // Used by data seeder or test setup
    public void seedUsers(Collection<User> users) {
        for (User user : users) {
            saveUser(user);
        }
    }
}
