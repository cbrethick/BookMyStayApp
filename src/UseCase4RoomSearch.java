import java.util.HashMap;
import java.util.Map;

/**
 * UseCase4RoomSearch
 *
 * Demonstrates read-only room search functionality.
 * Guests can view available rooms and pricing without
 * modifying the inventory state.
 *
 * @author Rethick
 * @version 4.0
 */


/* ---------- Room Domain Model ---------- */

abstract class Room {

    protected String roomType;
    protected int beds;
    protected double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getBeds() {
        return beds;
    }

    public double getPrice() {
        return price;
    }

    public void displayDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : ₹" + price);
    }
}


/* ---------- Concrete Room Types ---------- */

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 2000);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 3500);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 6000);
    }
}


/* ---------- Centralized Inventory ---------- */

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0);   // unavailable example
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getAllInventory() {
        return inventory;
    }
}


/* ---------- Search Service ---------- */

class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms() {

        System.out.println("\nAvailable Rooms:");

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            if (available > 0) {

                room.displayDetails();
                System.out.println("Available Rooms : " + available);
                System.out.println("---------------------------");

            }
        }
    }
}



/* ---------- Application Entry ---------- */

public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   BookMyStayApp - Version 4.0");
        System.out.println("   Room Search System");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();

        RoomSearchService searchService = new RoomSearchService(inventory);

        searchService.searchAvailableRooms();

        System.out.println("\nSearch completed. System state unchanged.");
    }
}