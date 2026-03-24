import java.util.*;

// Booking Request Class
class BookingRequest {
    String guestName;
    int roomsRequested;

    public BookingRequest(String guestName, int roomsRequested) {
        this.guestName = guestName;
        this.roomsRequested = roomsRequested;
    }
}

// Thread-safe Booking Queue
class BookingQueue {
    private Queue<BookingRequest> queue = new LinkedList<>();

    public synchronized void addRequest(BookingRequest request) {
        queue.add(request);
        System.out.println("Request added: " + request.guestName +
                " wants " + request.roomsRequested + " room(s)");
    }

    public synchronized BookingRequest getRequest() {
        return queue.poll();
    }
}

// Shared Inventory (Critical Section)
class HotelInventory {
    private int availableRooms;

    public HotelInventory(int rooms) {
        this.availableRooms = rooms;
    }

    // Critical section
    public synchronized boolean allocateRoom(String guestName, int rooms) {
        if (rooms <= availableRooms) {
            System.out.println("Allocating " + rooms + " room(s) to " + guestName);
            availableRooms -= rooms;
            System.out.println("Rooms left: " + availableRooms);
            return true;
        } else {
            System.out.println("Booking failed for " + guestName +
                    " (Not enough rooms)");
            return false;
        }
    }
}

// Booking Processor (Thread)
class BookingProcessor extends Thread {
    private BookingQueue queue;
    private HotelInventory inventory;

    public BookingProcessor(BookingQueue queue, HotelInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {
        while (true) {
            BookingRequest request;

            synchronized (queue) {
                request = queue.getRequest();
            }

            if (request == null) {
                break;
            }

            inventory.allocateRoom(request.guestName, request.roomsRequested);

            try {
                Thread.sleep(100); // simulate processing delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Main Class
public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {

        BookingQueue queue = new BookingQueue();
        HotelInventory inventory = new HotelInventory(5);

        // Simulating multiple guests (concurrent requests)
        queue.addRequest(new BookingRequest("Alice", 2));
        queue.addRequest(new BookingRequest("Bob", 2));
        queue.addRequest(new BookingRequest("Charlie", 2));
        queue.addRequest(new BookingRequest("David", 1));

        // Multiple threads processing bookings
        BookingProcessor t1 = new BookingProcessor(queue, inventory);
        BookingProcessor t2 = new BookingProcessor(queue, inventory);
        BookingProcessor t3 = new BookingProcessor(queue, inventory);

        t1.start();
        t2.start();
        t3.start();
    }
}
