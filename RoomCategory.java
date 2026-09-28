
public enum RoomCategory {
    STANDARD(50.0),
    DELUXE(90.0),
    SUITE(150.0);

    private final double pricePerNight;

    RoomCategory(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
}