package entity;

import java.util.Objects;

public class RegularUser extends User {

    public RegularUser(String userId, String name, String email, String password) {
        super(userId, name, email, password, "REGULAR_USER");
    }

    @Override
    public void showMenu() {
        System.out.println("\n=== Regular User Menu ===");
        System.out.println("1. Browse Resources");
        System.out.println("2. Book a Resource");
        System.out.println("3. View My Bookings");
        System.out.println("4. Cancel a Booking");
        System.out.println("5. Logout");
    }

    // Optional: You can override equals/hashCode/toString here if needed
}
