import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Hotel {
    private static final String FILE_NAME = "reservations.txt";

    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private int nextId = 1;

    public Hotel() {
        // 3 Standard, 3 Deluxe and 2 Suite rooms
        for (int i = 101; i <= 103; i++) {
            rooms.add(new Room(i, RoomCategory.STANDARD));
        }
        for (int i = 201; i <= 203; i++) {
            rooms.add(new Room(i, RoomCategory.DELUXE));
        }
        for (int i = 301; i <= 302; i++) {
            rooms.add(new Room(i, RoomCategory.SUITE));
        }
        loadReservations();
    }

    // Shows available rooms. Pass null to show all categories.
    public void showAvailableRooms(RoomCategory category) {
        System.out.println("\n--- Available Rooms ---");
        System.out.printf("%-8s %-10s %-10s%n", "Room", "Category", "Price/Night");
        boolean found = false;
        for (Room r : rooms) {
            boolean matches = (category == null || r.getCategory() == category);
            if (r.isAvailable() && matches) {
                System.out.printf("%-8d %-10s $%-10.2f%n", r.getRoomNumber(),
                        r.getCategory(), r.getCategory().getPricePerNight());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available rooms found.");
        }
    }

    private Room findRoom(int roomNumber) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) {
                return r;
            }
        }
        return null;
    }

    public Reservation findReservation(int id) {
        for (Reservation res : reservations) {
            if (res.getId() == id) {
                return res;
            }
        }
        return null;
    }

    // Returns the new reservation, or null if booking is not possible
    public Reservation bookRoom(String guestName, int roomNumber, int nights) {
        Room room = findRoom(roomNumber);
        if (room == null || !room.isAvailable() || nights <= 0) {
            return null;
        }
        String cleanName = guestName.replace(",", " ").trim();
        double total = room.getCategory().getPricePerNight() * nights;
        Reservation res = new Reservation(nextId++, cleanName, roomNumber,
                room.getCategory(), nights, total, false);
        reservations.add(res);
        room.setAvailable(false);
        saveReservations();
        return res;
    }

    public boolean makePayment(int reservationId) {
        Reservation res = findReservation(reservationId);
        if (res == null || res.isPaid()) {
            return false;
        }
        res.setPaid(true);
        saveReservations();
        return true;
    }

    public boolean cancelReservation(int reservationId) {
        Reservation res = findReservation(reservationId);
        if (res == null) {
            return false;
        }
        Room room = findRoom(res.getRoomNumber());
        if (room != null) {
            room.setAvailable(true);
        }
        reservations.remove(res);
        saveReservations();
        return true;
    }

    public void viewAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        for (Reservation res : reservations) {
            res.printDetails();
        }
    }

    // Saves all bookings to a text file
    private void saveReservations() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Reservation res : reservations) {
                writer.println(res.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Loads bookings from the text file when the program starts
    private void loadReservations() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                Reservation res = Reservation.fromFileString(line);
                reservations.add(res);
                Room room = findRoom(res.getRoomNumber());
                if (room != null) {
                    room.setAvailable(false);
                }
                if (res.getId() >= nextId) {
                    nextId = res.getId() + 1;
                }
            }
        } catch (IOException | RuntimeException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
