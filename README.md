// ----------------------
// ENTITY CLASSES
// ----------------------

abstract class User {
    private String id;
    private String name;
    private String email;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}

class Admin extends User {
    public Admin(String id, String name, String email) {
        super(id, name, email);
    }
}

class ResourceManager extends User {
    public ResourceManager(String id, String name, String email) {
        super(id, name, email);
    }
}

class RegularUser extends User {
    public RegularUser(String id, String name, String email) {
        super(id, name, email);
    }
}

class Resource {
    private String id;
    private String name;
    private String type;
    private double costPerHour;

    public Resource(String id, String name, String type, double costPerHour) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.costPerHour = costPerHour;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public double getCostPerHour() { return costPerHour; }
}

class Booking {
    private User user;
    private Resource resource;
    private DateTimeRange timeRange;
    private double cost;

    public Booking(User user, Resource resource, DateTimeRange timeRange, double cost) {
        this.user = user;
        this.resource = resource;
        this.timeRange = timeRange;
        this.cost = cost;
    }

    public User getUser() { return user; }
    public Resource getResource() { return resource; }
    public DateTimeRange getTimeRange() { return timeRange; }
    public double getCost() { return cost; }
}

// ----------------------
// REPOSITORY CLASSES
// ----------------------

class UserRepository {
    private List<User> users = new ArrayList<>();

    public void save(User user) {
        users.add(user);
    }

    public User findByEmail(String email) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) return u;
        }
        return null;
    }

    public List<User> findAll() {
        return users;
    }
}

class ResourceRepository {
    private List<Resource> resources = new ArrayList<>();

    public void save(Resource resource) {
        resources.add(resource);
    }

    public List<Resource> findAll() {
        return resources;
    }

    public void delete(String id) {
        resources.removeIf(r -> r.getId().equals(id));
    }

    public Resource findById(String id) {
        for (Resource r : resources) {
            if (r.getId().equals(id)) return r;
        }
        return null;
    }
}

class BookingRepository {
    private List<Booking> bookings = new ArrayList<>();

    public void save(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> findByUser(User user) {
        return bookings.stream()
                       .filter(b -> b.getUser().getId().equals(user.getId()))
                       .collect(Collectors.toList());
    }

    public List<Booking> findAll() {
        return bookings;
    }

    public boolean hasOverlap(Resource resource, DateTimeRange newRange) {
        return bookings.stream().anyMatch(b ->
            b.getResource().getId().equals(resource.getId()) &&
            b.getTimeRange().overlaps(newRange)
        );
    }
}

// ----------------------
// CONTROLLER CLASSES
// ----------------------

class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void register(User user) {
        userService.registerUser(user);
    }

    public User login(String email) {
        return userService.login(email);
    }
}

class ResourceController {
    private ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public void addResource(Resource resource) {
        resourceService.addResource(resource);
    }

    public void listResources() {
        for (Resource r : resourceService.getAllResources()) {
            System.out.println(r.getName() + " | " + r.getType() + " | Rs." + r.getCostPerHour() + "/hr");
        }
    }
}

class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void bookResource(User user, Resource resource, DateTimeRange range) {
        if (bookingService.createBooking(user, resource, range)) {
            System.out.println("Booking successful!");
        } else {
            System.out.println("Booking failed due to time conflict.");
        }
    }

    public void viewUserBookings(User user) {
        for (Booking b : bookingService.getUserBookings(user)) {
            System.out.println(b.getResource().getName() + " from " +
                               b.getTimeRange().getStart() + " to " +
                               b.getTimeRange().getEnd() + ". Cost: Rs." + b.getCost());
        }
    }
}

// ----------------------
// SERVICE CLASSES
// ----------------------

class UserService {
    private UserRepository userRepo = new UserRepository();

    public void registerUser(User user) {
        userRepo.save(user);
    }

    public User login(String email) {
        return userRepo.findByEmail(email);
    }
}

class ResourceService {
    private ResourceRepository resourceRepo = new ResourceRepository();

    public void addResource(Resource resource) {
        resourceRepo.save(resource);
    }

    public List<Resource> getAllResources() {
        return resourceRepo.findAll();
    }

    public Resource getResourceById(String id) {
        return resourceRepo.findById(id);
    }

    public void deleteResource(String id) {
        resourceRepo.delete(id);
    }
}

class BookingService {
    private BookingRepository bookingRepo = new BookingRepository();

    public boolean createBooking(User user, Resource resource, DateTimeRange range) {
        if (bookingRepo.hasOverlap(resource, range)) {
            return false;
        }
        double cost = Calculator.calculateCost(range, resource.getCostPerHour());
        Booking booking = new Booking(user, resource, range, cost);
        bookingRepo.save(booking);
        return true;
    }

    public List<Booking> getUserBookings(User user) {
        return bookingRepo.findByUser(user);
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }
}

class ReportService {
    private BookingRepository bookingRepo = new BookingRepository();

    public void printPopularResources() {
        Map<String, Long> countMap = bookingRepo.findAll().stream()
            .collect(Collectors.groupingBy(b -> b.getResource().getName(), Collectors.counting()));

        countMap.entrySet().stream()
            .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
            .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue() + " bookings"));
    }

    public void printUserHistory(User user) {
        for (Booking b : bookingRepo.findByUser(user)) {
            System.out.println(b.getResource().getName() + " from " + b.getTimeRange().getStart() + " to " + b.getTimeRange().getEnd());
        }
    }
}
