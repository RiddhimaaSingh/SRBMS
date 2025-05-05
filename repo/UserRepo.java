package repo;

import entity.User;
import java.util.*;

public class UserRepo {
    private Map<String, User> users = new HashMap<>();

    public void save(User user) {
        users.put(user.getId(), user);
    }

    public User findByEmail(String email) {
        return users.values().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public Collection<User> findAll() {
        return users.values();
    }

    public User findById(String userId) {
        return users.get(userId);
    }
    
}