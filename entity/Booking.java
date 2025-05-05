package entity;

public class Booking {
    private static int idCounter = 1;
    private String bookingId;
    private User user;
    private Resource resource;
    private DateTimeRange dateTimeRange;
    private double cost;

    public Booking(User user, Resource resource, DateTimeRange range, double cost) {
        this.bookingId = "BKG" + idCounter++;
        this.user = user;
        this.resource = resource;
        this.dateTimeRange = range;
        this.cost = cost;
    }

    public String getBookingId() { return bookingId; }
    public User getUser() { return user; }
    public Resource getResource() { return resource; }
    public DateTimeRange getDateTimeRange() { return dateTimeRange; }
    public double getCost() { return cost; }

    @Override
    public String toString() {
        return bookingId + ": " + user.getName() + " booked " + resource.getName() +
               " [" + dateTimeRange + "] ₹" + cost;
    }
}
