package entity;

public class RegularUser extends User {
    public RegularUser(String id, String name, String email, String password) {
        super(id, name, email, password, "user");
    }

    @Override
    public void showMenu() {
        System.out.println("1. View Resources");
        System.out.println("2. Book a Resource");
        System.out.println("3. View Booking History");
        System.out.println("4. Logout");
    }
}