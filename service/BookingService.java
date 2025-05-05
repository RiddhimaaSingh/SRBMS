package service;

import entity.Booking;
import entity.DateTimeRange;
import entity.Resource;
import entity.User;
import java.util.List;
import repo.BookingRepo;
import repo.ResourceRepo;
import util.Calculator;

public class BookingService {
    private final BookingRepo bookingRepo;
    private final ResourceRepo resourceRepo;

    public BookingService(BookingRepo bookingRepo, ResourceRepo resourceRepo) {
        this.bookingRepo = bookingRepo;
        this.resourceRepo = resourceRepo;
    }

    public boolean bookResource(User user, String resourceId, DateTimeRange range) {
        Resource resource = resourceRepo.findById(resourceId);
        if (resource == null) {
            System.out.println("Resource not found.");
            return false;
        }

        // Check for overlapping bookings
        List<Booking> existing = bookingRepo.getAllBookingsForResource(resourceId);
        for (Booking b : existing) {
            if (b.getDateTimeRange().overlapsWith(range)) {
                System.out.println("Resource is already booked for the selected time range.");
                return false;
            }
        }

        double cost = Calculator.calculateCost(range, resource.getCostPerHour());
        Booking booking = new Booking(user, resource, range, cost);
        bookingRepo.save(booking);
        System.out.println("Booking successful! Booking ID: " + booking.getBookingId());
        return true;
    }

    public List<Booking> getBookingsForUser(String userId) {
        return bookingRepo.getAllBookings().stream()
                .filter(b -> b.getUser().getId().equals(userId))
                .toList();
    }

    public boolean cancelBooking(String bookingId) {
        return bookingRepo.delete(bookingId);
    }
}