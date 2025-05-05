package repo;

import entity.Booking;
import java.util.*;
import java.util.stream.Collectors;

public class BookingRepo {
    private Map<String, Booking> bookings = new HashMap<>();

    public void save(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
    }

    public boolean delete(String bookingId) {
        return bookings.remove(bookingId) != null;
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }

    public List<Booking> getAllBookingsForResource(String resourceId) {
        return bookings.values().stream()
                .filter(b -> b.getResource().getId().equals(resourceId))
                .collect(Collectors.toList());
    }
}