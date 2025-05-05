package entity;

public class Resource {
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

    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setCostPerHour(double costPerHour) { this.costPerHour = costPerHour; }

    @Override
    public String toString() {
        return "[" + type + "] " + name + " - ₹" + costPerHour + "/hr";
    }
}