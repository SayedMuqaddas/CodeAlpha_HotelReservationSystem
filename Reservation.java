
public class Reservation {
    private int id;
    private String guestName;
    private int roomNumber;
    private RoomCategory category;
    private int nights;
    private double totalAmount;
    private boolean paid;

    public Reservation(int id, String guestName, int roomNumber,
                       RoomCategory category, int nights,
                       double totalAmount, boolean paid) {
        this.id = id;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.category = category;
        this.nights = nights;
        this.totalAmount = totalAmount;
        this.paid = paid;
    }

    public int getId() {
        return id;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    // Converts the booking into one line of text for saving in a file
    public String toFileString() {
        return id + "," + guestName + "," + roomNumber + "," + category
                + "," + nights + "," + totalAmount + "," + paid;
    }

    // Creates a booking back from a line of the file
    public static Reservation fromFileString(String line) {
        String[] p = line.split(",");
        return new Reservation(
                Integer.parseInt(p[0]),
                p[1],
                Integer.parseInt(p[2]),
                RoomCategory.valueOf(p[3]),
                Integer.parseInt(p[4]),
                Double.parseDouble(p[5]),
                Boolean.parseBoolean(p[6]));
    }

    public void printDetails() {
        System.out.println("----------------------------------");
        System.out.println("Booking ID   : " + id);
        System.out.println("Guest name   : " + guestName);
        System.out.println("Room number  : " + roomNumber);
        System.out.println("Category     : " + category);
        System.out.println("Nights       : " + nights);
        System.out.printf("Total amount : $%.2f%n", totalAmount);
        System.out.println("Payment      : " + (paid ? "PAID" : "PENDING"));
        System.out.println("----------------------------------");
    }
}