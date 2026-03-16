import java.util.*;

// Represents an optional add-on service
class Service {
    private String serviceName;
    private double price;

    public Service(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return serviceName + " ($" + price + ")";
    }
}

// Manages add-on services for reservations
class AddOnServiceManager {

    // Map ReservationID -> List of Services
    private Map<String, List<Service>> reservationServices = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, Service service) {

        reservationServices.putIfAbsent(reservationId, new ArrayList<>());

        reservationServices.get(reservationId).add(service);

        System.out.println("Service Added: " + service.getServiceName()
                + " to Reservation " + reservationId);
    }

    // Calculate total cost of services
    public double calculateServiceCost(String reservationId) {

        List<Service> services = reservationServices.get(reservationId);

        if (services == null) {
            return 0;
        }

        double total = 0;

        for (Service s : services) {
            total += s.getPrice();
        }

        return total;
    }

    // Display services for a reservation
    public void showServices(String reservationId) {

        List<Service> services = reservationServices.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected for Reservation " + reservationId);
            return;
        }

        System.out.println("\nServices for Reservation " + reservationId + ":");

        for (Service s : services) {
            System.out.println("- " + s);
        }

        System.out.println("Total Add-On Cost: $" + calculateServiceCost(reservationId));
    }
}

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        // Example reservation IDs (from previous booking system)
        String reservation1 = "RES-101";
        String reservation2 = "RES-102";

        // Available services
        Service breakfast = new Service("Breakfast", 20);
        Service airportPickup = new Service("Airport Pickup", 40);
        Service spaAccess = new Service("Spa Access", 60);
        Service extraBed = new Service("Extra Bed", 25);

        // Guest selects services
        manager.addService(reservation1, breakfast);
        manager.addService(reservation1, spaAccess);
        manager.addService(reservation1, extraBed);

        manager.addService(reservation2, airportPickup);
        manager.addService(reservation2, breakfast);

        // Display services for each reservation
        manager.showServices(reservation1);
        manager.showServices(reservation2);
    }
}