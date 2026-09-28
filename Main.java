import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        int choice;

        do {
            System.out.println("\n===== Hotel Reservation System =====");
            System.out.println("1. Search available rooms");
            System.out.println("2. Book a room");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Make payment");
            System.out.println("5. View booking details");
            System.out.println("6. View all bookings");
            System.out.println("7. Exit");
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    searchRooms(hotel);
                    break;
                case 2:
                    bookRoom(hotel);
                    break;
                case 3:
                    int cancelId = readInt("Enter booking ID to cancel: ");
                    if (hotel.cancelReservation(cancelId)) {
                        System.out.println("Reservation cancelled successfully.");
                    } else {
                        System.out.println("Booking not found.");
                    }
                    break;
                case 4:
                    int payId = readInt("Enter booking ID to pay: ");
                    processPayment(hotel, payId);
                    break;
                case 5:
                    int viewId = readInt("Enter booking ID: ");
                    Reservation res = hotel.findReservation(viewId);
                    if (res == null) {
                        System.out.println("Booking not found.");
                    } else {
                        res.printDetails();
                    }
                    break;
                case 6:
                    hotel.viewAllReservations();
                    break;
                case 7:
                    System.out.println("Thank you for using the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        sc.close();
    }

    private static void searchRooms(Hotel hotel) {
        System.out.println("\n1. Standard  2. Deluxe  3. Suite  4. All");
        int c = readInt("Choose a category: ");
        switch (c) {
            case 1:
                hotel.showAvailableRooms(RoomCategory.STANDARD);
                break;
            case 2:
                hotel.showAvailableRooms(RoomCategory.DELUXE);
                break;
            case 3:
                hotel.showAvailableRooms(RoomCategory.SUITE);
                break;
            case 4:
                hotel.showAvailableRooms(null);
                break;
            default:
                System.out.println("Invalid category.");
        }
    }

    private static void bookRoom(Hotel hotel) {
        hotel.showAvailableRooms(null);
        int roomNumber = readInt("\nEnter room number to book: ");
        System.out.print("Enter guest name: ");
        String name = sc.nextLine();
        int nights = readInt("Enter number of nights: ");

        Reservation res = hotel.bookRoom(name, roomNumber, nights);
        if (res == null) {
            System.out.println("Booking failed. Room not available or invalid input.");
            return;
        }
        System.out.println("Room booked successfully!");
        res.printDetails();

        System.out.print("Pay now? (y/n): ");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            processPayment(hotel, res.getId());
        } else {
            System.out.println("You can pay later using option 4.");
        }
    }

    // Payment simulation (no real payment)
    private static void processPayment(Hotel hotel, int id) {
        Reservation res = hotel.findReservation(id);
        if (res == null) {
            System.out.println("Booking not found.");
            return;
        }
        if (res.isPaid()) {
            System.out.println("This booking is already paid.");
            return;
        }
        System.out.printf("Amount to pay: $%.2f%n", res.getTotalAmount());
        System.out.println("1. Credit card  2. Cash");
        int method = readInt("Choose payment method: ");
        if (method != 1 && method != 2) {
            System.out.println("Invalid payment method.");
            return;
        }
        System.out.println("Processing payment...");
        hotel.makePayment(id);
        System.out.println("Payment successful! Thank you.");
    }

    // Reads a number safely (no crash if user types text)
    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
