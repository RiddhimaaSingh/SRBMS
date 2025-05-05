package controller;

import entity.*;
import java.util.Scanner;
import repo.*;
import service.*;
public class MainController {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserRepo userRepo = new UserRepo();
        ResourceRepo resourceRepo = new ResourceRepo();
        BookingRepo bookingRepo = new BookingRepo();

        UserService userService = new UserService(userRepo);
        ResourceService resourceService = new ResourceService(resourceRepo);
        BookingService bookingService = new BookingService(bookingRepo, resourceRepo);
        ReportService reportService = new ReportService(bookingRepo, resourceRepo);

        // Preload users for testing
        userRepo.save(new Admin("1", "Admin User", "admin@example.com", "password123"));
        userRepo.save(new RegularUser("2", "Regular User", "user@example.com", "password456"));
        userRepo.save(new ResourceManager("3", "Manager User", "manager@example.com", "password789"));

        System.out.println("Welcome to Smart Resource Booking System (SRBMS)");

        while (true) {
            System.out.print("\nEmail: ");
            String email = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            User user = userService.login(email, password);

            if (user != null) {
                System.out.println("\nLogin successful. Welcome, " + user.getName() + "!");
                boolean running = true;
                while (running) {
                    user.showMenu();
                    System.out.print("Select option: ");
                    int option = Integer.parseInt(scanner.nextLine());

                    switch (user.getRole()) {
                        case "admin" -> {
                            switch (option) {
                                case 1 -> System.out.println("Add Resource [handled by ResourceService]");
                                case 2 -> userService.getAllUsers().forEach(System.out::println);
                                case 3 -> reportService.showAllBookings();
                                case 4 -> running = false;
                            }
                        }
                        case "manager" -> {
                            switch (option) {
                                case 1 -> System.out.println("View Requests");
                                case 2 -> System.out.println("Approve/Reject");
                                case 3 -> System.out.println("Update Availability");
                                case 4 -> running = false;
                            }
                        }
                        case "user" -> {
                            switch (option) {
                                case 1 -> resourceService.getAllResources().forEach(System.out::println);
                                case 2 -> System.out.println("Book a resource [use bookingService]");
                                case 3 -> bookingService.getBookingsForUser(user.getId()).forEach(System.out::println);
                                case 4 -> running = false;
                            }
                        }
                    }
                }
            } else {
                System.out.println("Invalid email or password. Try again.");
            }
        }
    }
}