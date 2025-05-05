package service;

import java.util.Map;
import java.util.stream.Collectors;
import repo.BookingRepo;
import repo.ResourceRepo;

public class ReportService {
    private final BookingRepo bookingRepo;
    private final ResourceRepo resourceRepo;

    public ReportService(BookingRepo bookingRepo, ResourceRepo resourceRepo) {
        this.bookingRepo = bookingRepo;
        this.resourceRepo = resourceRepo;
    }

    public void showMostBookedResources() {
        Map<String, Long> countMap = bookingRepo.getAllBookings().stream()
                .collect(Collectors.groupingBy(b -> b.getResource().getId(), Collectors.counting()));

        System.out.println("Most Booked Resources:");
        countMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> {
                    String resourceName = resourceRepo.findById(entry.getKey()).getName();
                    System.out.println(resourceName + " - " + entry.getValue() + " bookings");
                });
    }

    public void showAllBookings() {
        bookingRepo.getAllBookings().forEach(System.out::println);
    }

    public void showUserHistory(String userId) {
        bookingRepo.getAllBookings().stream()
                .filter(b -> b.getUser().getId().equals(userId))
                .forEach(System.out::println);
    }
}