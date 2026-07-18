package model;

public enum BookingStatus {

    CONFIRMED,
    CHECKED_IN,
    CHECKED_OUT,
    CANCELLED;

    @Override
    public String toString() {
        switch (this) {
            case CONFIRMED:
                return "Confirmed";
            case CHECKED_IN:
                return "Checked In";
            case CHECKED_OUT:
                return "Checked Out";
            case CANCELLED:
                return "Cancelled";
            default:
                return name();
        }
    }
}