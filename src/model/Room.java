package model;

public class Room {

    private int roomId;
    private RoomType roomType;
    private double pricePerNight;
    private RoomStatus status;
    private int maxGuests;

    public Room(int roomId, RoomType roomType) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.pricePerNight = roomType.getPricePerNight();
        this.maxGuests = roomType.getMaxGuests();
        this.status = RoomStatus.AVAILABLE;
    }

    // Getters
    public int getRoomId() {
        return roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    // Setters
    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return "----------------------------------------\n" +
               "Room ID        : " + roomId + "\n" +
               "Room Type      : " + roomType.getDisplayName() + "\n" +
               "Price/Night    : Rs. " + String.format("%.2f", pricePerNight) + "\n" +
               "Max Guests     : " + maxGuests + "\n" +
               "Room Status    : " + status + "\n" +
               "----------------------------------------";
    }
}