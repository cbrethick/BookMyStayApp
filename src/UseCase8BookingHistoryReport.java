import java.util.*;

// Reservation model
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private double totalCost;

    public Reservation(String reservationId, String guestName, String roomType, double totalCost) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.totalCost = totalCost;
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

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
               ", Guest: " + guestName +
               ", Room Type: " + roomType +
               ", Total Cost: $" + totalCost;
    }
}

// Booking History storage
class BookingHistory {

    // List preserves insertion order
    private List<Reservation> reservations = new ArrayList<>();

    // Add confirmed reservation to history
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        System.out.println("Reservation stored in history: " + reservation.getReservationId());
    }

    // Retrieve all reservations
    public List<Reservation> getReservations() {
        return reservations;
    }
}

// Reporting Service
class BookingReportService {

    // Display full booking history
    public void printBookingHistory(List<Reservation> reservations) {

        System.out.println("\n===== Booking History =====");

        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }

    // Generate summary report
    public void generateSummaryReport(List<Reservation> reservations) {

        System.out.println("\n===== Booking Summary Report =====");

        int totalBookings = reservations.size();
        double totalRevenue = 0;

        Map<String, Integer> roomTypeCount = new HashMap<>();

        for (Reservation r : reservations) {

            totalRevenue += r.getTotalCost();

            roomTypeCount.put(
                    r.getRoomType(),
                    roomTypeCount.getOrDefault(r.getRoomType(), 0) + 1
            );
        }

        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Revenue: $" + totalRevenue);

        System.out.println("\nRoom Type Distribution:");

        for (String type : roomTypeCount.keySet()) {
            System.out.println(type + " -> " + roomTypeCount.get(type));
        }
    }
}

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulating confirmed bookings
        Reservation r1 = new Reservation("RES-201", "Alice", "STANDARD", 120);
        Reservation r2 = new Reservation("RES-202", "Bob", "DELUXE", 200);
        Reservation r3 = new Reservation("RES-203", "Charlie", "SUITE", 350);
        Reservation r4 = new Reservation("RES-204", "David", "STANDARD", 120);

        // Store confirmed reservations
        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);
        history.addReservation(r4);

        // Admin views booking history
        reportService.printBookingHistory(history.getReservations());

        // Admin generates summary report
        reportService.generateSummaryReport(history.getReservations());
    }
}