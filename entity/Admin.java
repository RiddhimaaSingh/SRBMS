package entity;

public class Admin extends User {
    public Admin(String id, String name, String email, String password) {
        super(id, name, email, password, "admin");
    }

    @Override
    public void showMenu() {
        System.out.println("1. Add Resource");
        System.out.println("2. View All Users");
        System.out.println("3. View All Bookings");
        System.out.println("4. Logout");
    }
}