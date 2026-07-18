package model;

public enum RoomStatus {

    AVAILABLE,
    BOOKED,
    OCCUPIED,
    MAINTENANCE;

    @Override
    public String toString() {
        switch (this) {
            case AVAILABLE:
                return "Available";
            case BOOKED:
                return "Booked";
            case OCCUPIED:
                return "Occupied";
            case MAINTENANCE:
                return "Maintenance";
            default:
                return name();
        }
    }
}