import java.util.LinkedList;
import java.util.Queue;

/**
 * UseCase5BookingRequestQueue
 *
 * Demonstrates how booking requests are collected using
 * a Queue data structure following the FIFO principle.
 *
 * No room allocation or inventory updates occur here.
 * Requests are simply stored and prepared for processing.
 *
 * @author Rethick
 * @version 5.0
 */


/* ---------- Reservation Class ---------- */

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

    public void displayReservation() {
        System.out.println("Guest : " + guestName + " | Requested Room : " + roomType);
    }
}



/* ---------- Booking Request Queue ---------- */

class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName());
    }

    // Display all queued requests
    public void displayQueue() {

        System.out.println("\nCurrent Booking Request Queue:");

        for (Reservation r : queue) {
            r.displayReservation();
        }
    }
}



/* ---------- Application Entry ---------- */

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   BookMyStayApp - Version 5.0");
        System.out.println("   Booking Request Queue System");
        System.out.println("=================================");

        BookingRequestQueue requestQueue = new BookingRequestQueue();


        // Simulating guest booking requests
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");


        // Add requests to queue (FIFO order)
        requestQueue.addRequest(r1);
        requestQueue.addRequest(r2);
        requestQueue.addRequest(r3);


        // Show queued requests
        requestQueue.displayQueue();

        System.out.println("\nAll requests stored in arrival order.");
        System.out.println("No inventory changes performed.");
    }
}