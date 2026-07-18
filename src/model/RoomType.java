package model;

public enum RoomType {

    SINGLE("Single", 2000.0, 1),
    DOUBLE("Double", 2500.0, 2),
    DELUXE("Deluxe", 3500.0, 3),
    SUITE("Suite", 5000.0, 4);

    private final String displayName;
    private final double pricePerNight;
    private final int maxGuests;

    RoomType(String displayName, double pricePerNight, int maxGuests) {
        this.displayName = displayName;
        this.pricePerNight = pricePerNight;
        this.maxGuests = maxGuests;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    @Override
    public String toString() {
        return displayName;
    }
}