package entity;

public class ResourceManager extends User {
    public ResourceManager(String id, String name, String email, String password) {
        super(id, name, email, password, "manager");
    }

    @Override
    public void showMenu() {
        System.out.println("1. View Requests");
        System.out.println("2. Approve/Reject Requests");
        System.out.println("3. Update Availability");
        System.out.println("4. Logout");
    }
}