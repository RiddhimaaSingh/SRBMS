package entity;

public abstract class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;

    public User(String id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public abstract void showMenu();

    @Override
    public String toString() {
        return "User{id='" + id + "', name='" + name + "', email='" + email + "', role='" + role + "'}";
    }
}