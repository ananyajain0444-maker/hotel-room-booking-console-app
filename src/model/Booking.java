package model;

import java.time.LocalDate;

public class Booking {

    private String bookingId;
    private Guest guest;
    private Room room;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private int numberOfGuests;
    private int numberOfNights;

    private double totalAmount;

    private BookingStatus bookingStatus;

    public Booking(String bookingId,
                   Guest guest,
                   Room room,
                   LocalDate checkInDate,
                   LocalDate checkOutDate,
                   int numberOfGuests,
                   int numberOfNights,
                   double totalAmount) {

        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.numberOfNights = numberOfNights;
        this.totalAmount = totalAmount;

        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    // Getters

    public String getBookingId() {
        return bookingId;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    // Setters

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {

        return "\n========== BOOKING DETAILS ==========\n" +
                "Booking ID      : " + bookingId + "\n" +
                "Guest Name      : " + guest.getGuestName() + "\n" +
                "Phone Number    : " + guest.getPhoneNumber() + "\n" +
                "Email           : " + guest.getEmail() + "\n" +
                "Room ID         : " + room.getRoomId() + "\n" +
                "Room Type       : " + room.getRoomType().getDisplayName() + "\n" +
                "Check-In Date   : " + checkInDate + "\n" +
                "Check-Out Date  : " + checkOutDate + "\n" +
                "Guests          : " + numberOfGuests + "\n" +
                "Nights          : " + numberOfNights + "\n" +
                "Total Amount    : Rs. " + String.format("%.2f", totalAmount) + "\n" +
                "Booking Status  : " + bookingStatus + "\n" +
                "=====================================\n";
    }
}