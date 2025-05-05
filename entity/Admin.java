package entity;

public class Admin extends User {

    public Admin(String userId, String name, String email, String password) {
        super(userId, name, email, password, "admin");
    }

    @Override
    public void showMenu() {
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1. Add Resource");
        System.out.println("2. View All Users");
        System.out.println("3. View Reports");
        System.out.println("4. Logout");
    }
}