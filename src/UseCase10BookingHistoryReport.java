import java.util.*;

// Reservation model
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private String roomId;

    public Reservation(String reservationId, String guestName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public String toString() {
        return "Reservation ID: " + reservationId +
                ", Guest: " + guestName +
                ", Room Type: " + roomType +
                ", Room ID: " + roomId;
    }
}

// Booking & Cancellation Service
class CancellationService {

    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Reservation> reservations = new HashMap<>();
    private Stack<String> rollbackRoomStack = new Stack<>();

    private int reservationCounter = 1;
    private int roomCounter = 1;

    public CancellationService() {
        inventory.put("STANDARD", 2);
        inventory.put("DELUXE", 1);
        inventory.put("SUITE", 1);
    }

    // Create booking
    public void createBooking(String guestName, String roomType) {

        if (!inventory.containsKey(roomType) || inventory.get(roomType) <= 0) {
            System.out.println("Booking Failed: No available rooms for " + roomType);
            return;
        }

        String reservationId = "RES-" + reservationCounter++;
        String roomId = roomType.substring(0,2) + "-" + roomCounter++;

        Reservation reservation = new Reservation(reservationId, guestName, roomType, roomId);

        reservations.put(reservationId, reservation);

        inventory.put(roomType, inventory.get(roomType) - 1);

        System.out.println("Booking Confirmed -> " + reservation);
    }

    // Cancel booking
    public void cancelBooking(String reservationId) {

        if (!reservations.containsKey(reservationId)) {
            System.out.println("Cancellation Failed: Reservation does not exist -> " + reservationId);
            return;
        }

        Reservation reservation = reservations.remove(reservationId);

        String roomType = reservation.getRoomType();
        String roomId = reservation.getRoomId();

        // Push released room into rollback stack
        rollbackRoomStack.push(roomId);

        // Restore inventory
        inventory.put(roomType, inventory.get(roomType) + 1);

        System.out.println("Cancellation Successful for Reservation -> " + reservationId);
        System.out.println("Room Released -> " + roomId);
    }

    public void showInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " -> " + inventory.get(type));
        }
    }

    public void showRollbackStack() {
        System.out.println("\nRollback Stack (Released Rooms): " + rollbackRoomStack);
    }
}

public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        CancellationService service = new CancellationService();

        // Create bookings
        service.createBooking("Alice", "STANDARD");
        service.createBooking("Bob", "DELUXE");

        // Cancel booking
        service.cancelBooking("RES-1");

        // Attempt invalid cancellation
        service.cancelBooking("RES-99");

        // System state check
        service.showInventory();
        service.showRollbackStack();
    }
}
