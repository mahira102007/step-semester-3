
abstract class Vehicle {
    private final String name;
    private boolean available = true;

    public Vehicle(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Vehicle name required");
        this.name = name;
    }

    public String getName() { return name; }
    public boolean isAvailable() { return available; }
    void setAvailable(boolean value) { available = value; }

    public abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    Sedan(String name) { super(name); }
    public double calculateCharge(int days) { return days * 50.0; }
}

class SUV extends Vehicle {
    SUV(String name) { super(name); }
    public double calculateCharge(int days) { return days * 80.0; }
}

class Truck extends Vehicle {
    Truck(String name) { super(name); }
    public double calculateCharge(int days) { return days * 120.0; }
}

class Rental {
    private final String customer;
    private final Vehicle vehicle;
    private final int days;
    private boolean returned;

    Rental(String customer, Vehicle vehicle, int days) {
        if (customer == null || customer.trim().isEmpty())
            throw new IllegalArgumentException("Customer required");
        if (days <= 0)
            throw new IllegalArgumentException("Days must be positive");
        if (!vehicle.isAvailable())
            throw new IllegalStateException(
                vehicle.getName() + " is currently unavailable."
            );

        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        vehicle.setAvailable(false);
    }

    void showRental() {
        System.out.printf(
            "%s rented successfully by %s. Rental charge: $%.2f.%n",
            vehicle.getName(), customer, vehicle.calculateCharge(days)
        );
    }

    void returnVehicle() {
        if (!returned) {
            vehicle.setAvailable(true);
            returned = true;
            System.out.println(vehicle.getName()
                    + " returned by " + customer + ".");
        }
    }
}

public class week8p1 {
    public static void main(String[] args) {
        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");

        Rental r1 = new Rental("Customer 1", sedan, 3);
        r1.showRental();

        try {
            new Rental("Customer 2", sedan, 2);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        r1.returnVehicle();

        Rental r2 = new Rental("Customer 3", suv, 5);
        r2.showRental();
    }
}
