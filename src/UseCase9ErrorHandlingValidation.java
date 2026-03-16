import java.util.*;

// Custom Exception for invalid booking
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation class
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String toString() {
        return "Reservation ID: " + reservationId +
                ", Guest: " + guestName +
                ", Room Type: " + roomType;
    }
}

// Booking Validator
class InvalidBookingValidator {

    private Map<String, Integer> inventory;

    public InvalidBookingValidator(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public void validate(String guestName, String roomType) throws InvalidBookingException {

        // Validate guest name
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // Validate room type
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type selected: " + roomType);
        }

        // Validate availability
        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No available rooms for type: " + roomType);
        }
    }
}

// Booking Service
class BookingService {

    private Map<String, Integer> inventory = new HashMap<>();
    private List<Reservation> reservations = new ArrayList<>();
    private int reservationCounter = 1;

    public BookingService() {
        inventory.put("STANDARD", 2);
        inventory.put("DELUXE", 1);
        inventory.put("SUITE", 1);
    }

    public void createBooking(String guestName, String roomType) {

        InvalidBookingValidator validator = new InvalidBookingValidator(inventory);

        try {

            // Validate input first
            validator.validate(guestName, roomType);

            // Create reservation
            String reservationId = "RES-" + reservationCounter++;

            Reservation reservation = new Reservation(reservationId, guestName, roomType);

            // Update inventory safely
            inventory.put(roomType, inventory.get(roomType) - 1);

            reservations.add(reservation);

            System.out.println("Booking Confirmed: " + reservation);

        } catch (InvalidBookingException e) {

            System.out.println("Booking Failed: " + e.getMessage());

        }
    }

    public void showInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " -> " + inventory.get(type));
        }
    }
}

public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        BookingService bookingService = new BookingService();

        // Valid booking
        bookingService.createBooking("Alice", "STANDARD");

        // Invalid room type
        bookingService.createBooking("Bob", "PRESIDENTIAL");

        // Empty guest name
        bookingService.createBooking("", "DELUXE");

        // Inventory exhaustion example
        bookingService.createBooking("Charlie", "DELUXE");
        bookingService.createBooking("David", "DELUXE");

        // Show remaining inventory
        bookingService.showInventory();
    }
}