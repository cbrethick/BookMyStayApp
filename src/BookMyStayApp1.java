/**
 * UseCase2RoomInitialization
 *
 * Demonstrates object modeling using abstraction and inheritance
 * for a Hotel Booking System.
 *
 * This version initializes room types and displays their
 * availability using simple variables.
 *
 * @author Rethick
 * @version 2.1
 */

abstract class Room {

    private String roomType;
    private int beds;
    private double price;

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

    public abstract void displayDetails();
}


/* Single Room */
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 2000);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}


/* Double Room */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 3500);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}


/* Suite Room */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 6000);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}



public class BookMyStayApp1 {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   BookMyStayApp - Version 2.1");
        System.out.println("   Room Availability");
        System.out.println("=================================");


        // Create Room Objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();


        // Static Availability
        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;


        System.out.println("\nSingle Room Details:");
        single.displayDetails();
        System.out.println("Available Rooms : " + singleAvailability);


        System.out.println("\nDouble Room Details:");
        doubleRoom.displayDetails();
        System.out.println("Available Rooms : " + doubleAvailability);


        System.out.println("\nSuite Room Details:");
        suite.displayDetails();
        System.out.println("Available Rooms : " + suiteAvailability);


        System.out.println("\nApplication terminated.");
    }
}