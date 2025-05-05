package com.srbms.entity;

import java.time.LocalDateTime;

public class Booking {
    private String bookingId;
    private User user;
    private Resource resource;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double cost;

    public Booking(String bookingId, User user, Resource resource,
                   LocalDateTime startTime, LocalDateTime endTime, double cost) {
        this.bookingId = bookingId;
        this.user = user;
        this.resource = resource;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
    }

    // Getters
    public String getBookingId() { return bookingId; }
    public User getUser() { return user; }
    public Resource getResource() { return resource; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public double getCost() { return cost; }

    // Setters
    public void setCost(double cost) { this.cost = cost; }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", user=" + user.getName() +
                ", resource=" + resource.getName() +
                ", start=" + startTime +
                ", end=" + endTime +
                ", cost=" + cost +
                '}';
    }
}
