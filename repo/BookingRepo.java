package com.srbms.repo;

import com.srbms.entity.Booking;

import java.util.*;

public class BookingRepo {
    private Map<String, Booking> bookingMap = new HashMap<>(); // key = bookingId

    // Add a new booking
    public void addBooking(Booking booking) {
        bookingMap.put(booking.getBookingId(), booking);
    }

    // Get a booking by ID
    public Booking getBookingById(String bookingId) {
        return bookingMap.get(bookingId);
    }

    // Remove a booking
    public boolean removeBooking(String bookingId) {
        return bookingMap.remove(bookingId) != null;
    }

    // Get all bookings
    public Collection<Booking> getAllBookings() {
        return bookingMap.values();
    }

    // Get bookings made by a specific user
    public List<Booking> getBookingsByUserId(String userId) {
        List<Booking> result = new ArrayList<>();
        for (Booking booking : bookingMap.values()) {
            if (booking.getUser().getUserId().equals(userId)) {
                result.add(booking);
            }
        }
        return result;
    }

    // Get bookings for a specific resource (e.g., for overlap checking)
    public List<Booking> getBookingsByResourceId(String resourceId) {
        List<Booking> result = new ArrayList<>();
        for (Booking booking : bookingMap.values()) {
            if (booking.getResource().getResourceId().equals(resourceId)) {
                result.add(booking);
            }
        }
        return result;
    }

    // Check if a booking ID already exists
    public boolean bookingExists(String bookingId) {
        return bookingMap.containsKey(bookingId);
    }
}
