package entity;

public class RegularUser extends User {

    public RegularUser(String userId, String name, String email, String password) {
        super(userId, name, email, password, "user");
    }

    @Override
    public void showMenu() {
        System.out.println("\n--- User Menu ---");
        System.out.println("1. Browse Resources");
        System.out.println("2. Book a Resource");
        System.out.println("3. View My Bookings");
        System.out.println("4. Logout");
    }
}