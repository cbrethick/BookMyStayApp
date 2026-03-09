import java.util.*;

/**
 * UseCase6RoomAllocationService
 *
 * Demonstrates reservation confirmation and safe room allocation.
 * Booking requests are processed in FIFO order and unique room IDs
 * are generated to prevent double-booking.
 *
 * @author Rethick
 * @version 6.0
 */


/* ---------- Reservation ---------- */

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}


/* ---------- Booking Queue ---------- */

class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.add(r);
    }

    public Reservation getNextRequest() {
        return queue.poll(); // FIFO
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}


/* ---------- Inventory Service ---------- */

class RoomInventory {

    private HashMap<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}


/* ---------- Allocation Service ---------- */

class RoomAllocationService {

    private RoomInventory inventory;

    private Set<String> allocatedRoomIds = new HashSet<>();

    private HashMap<String, Set<String>> roomAssignments = new HashMap<>();

    public RoomAllocationService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void allocateRoom(Reservation reservation) {

        String roomType = reservation.getRoomType();

        if (inventory.getAvailability(roomType) <= 0) {
            System.out.println("No rooms available for " + reservation.getGuestName());
            return;
        }

        // Generate unique room ID
        String roomId = roomType.replace(" ", "").toUpperCase()
                + "-" + (allocatedRoomIds.size() + 1);

        allocatedRoomIds.add(roomId);

        roomAssignments
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

        inventory.decrementRoom(roomType);

        System.out.println("Reservation Confirmed:");
        System.out.println("Guest: " + reservation.getGuestName());
        System.out.println("Room Type: " + roomType);
        System.out.println("Assigned Room ID: " + roomId);
        System.out.println("-------------------------");
    }
}


/* ---------- Application Entry ---------- */

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   BookMyStayApp - Version 6.0");
        System.out.println("   Reservation Allocation System");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();

        BookingRequestQueue queue = new BookingRequestQueue();

        RoomAllocationService allocator = new RoomAllocationService(inventory);


        // Simulated booking requests
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Suite Room"));
        queue.addRequest(new Reservation("David", "Suite Room")); // no availability


        // Process queue FIFO
        while (!queue.isEmpty()) {

            Reservation request = queue.getNextRequest();

            allocator.allocateRoom(request);
        }

        inventory.displayInventory();

        System.out.println("\nAll requests processed.");
    }
}