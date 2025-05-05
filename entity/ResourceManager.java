package entity;
public class ResourceManager extends User {

    public ResourceManager(String userId, String name, String email, String password) {
        super(userId, name, email, password, "manager");
    }

    @Override
    public void showMenu() {
        System.out.println("\n--- Resource Manager Menu ---");
        System.out.println("1. View Resource Requests");
        System.out.println("2. Approve/Reject Bookings");
        System.out.println("3. Update Resource Availability");
        System.out.println("4. Logout");
    }
}