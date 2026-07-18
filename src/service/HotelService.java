package service;

import model.*;
import util.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelService {

    private List<Room> rooms;
    private List<Booking> bookings;

    public HotelService() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        initializeRooms();
    }

    /*
     * Initialize Sample Rooms
     */
    private void initializeRooms() {

        // Single
        rooms.add(new Room(101, RoomType.SINGLE));
        rooms.add(new Room(102, RoomType.SINGLE));
        rooms.add(new Room(103, RoomType.SINGLE));

        // Double
        rooms.add(new Room(201, RoomType.DOUBLE));
        rooms.add(new Room(202, RoomType.DOUBLE));
        rooms.add(new Room(203, RoomType.DOUBLE));

        // Deluxe
        rooms.add(new Room(301, RoomType.DELUXE));
        rooms.add(new Room(302, RoomType.DELUXE));

        // Suite
        rooms.add(new Room(401, RoomType.SUITE));
        rooms.add(new Room(402, RoomType.SUITE));
    }

    /*
     * Display All Rooms
     */
    public void displayAllRooms() {

        System.out.println("\n========== ROOM LIST ==========\n");

        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    /*
     * Display Available Rooms
     */
    public void displayAvailableRooms() {

        boolean found = false;

        System.out.println("\n====== AVAILABLE ROOMS ======\n");

        for (Room room : rooms) {

            if (room.getStatus() == RoomStatus.AVAILABLE) {

                System.out.println(room);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms available.");
        }
    }

    /*
     * Search Rooms
     */
    public List<Room> searchRooms(RoomType type) {

        List<Room> result = new ArrayList<>();

        for (Room room : rooms) {

            if (room.getRoomType() == type &&
                    room.getStatus() == RoomStatus.AVAILABLE) {

                result.add(room);
            }
        }

        return result;
    }

    /*
     * Find Room
     */
    public Room findRoomById(int roomId) {

        for (Room room : rooms) {

            if (room.getRoomId() == roomId) {
                return room;
            }
        }

        return null;
    }

    /*
     * Get Rooms
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /*
     * Get Bookings
     */
    public List<Booking> getBookings() {
        return bookings;
    }
    /*
     * Create Booking
     */
    public Booking createBooking(Guest guest,
                                 int roomId,
                                 LocalDate checkInDate,
                                 LocalDate checkOutDate,
                                 int numberOfGuests) {

        Room room = findRoomById(roomId);

        if (room == null) {
            System.out.println("Invalid Room ID.");
            return null;
        }

        if (room.getStatus() != RoomStatus.AVAILABLE) {
            System.out.println("Room is already booked.");
            return null;
        }

        if (numberOfGuests > room.getMaxGuests()) {
            System.out.println("Maximum guest limit is " + room.getMaxGuests());
            return null;
        }

        int nights = BillCalculator.calculateNights(checkInDate, checkOutDate);

        if (nights <= 0) {
            System.out.println("Invalid Check-In / Check-Out dates.");
            return null;
        }

        double totalAmount = BillCalculator.calculateTotalBill(
                room.getPricePerNight(),
                checkInDate,
                checkOutDate
        );

        String bookingId = BookingIdGenerator.generateBookingId();

        Booking booking = new Booking(
                bookingId,
                guest,
                room,
                checkInDate,
                checkOutDate,
                numberOfGuests,
                nights,
                totalAmount
        );

        bookings.add(booking);

        room.setStatus(RoomStatus.BOOKED);

        FileManager.saveBooking(booking);

        System.out.println("\nBooking Created Successfully!");
        System.out.println("Booking ID : " + bookingId);

        return booking;
    }

    /*
     * View Booking
     */
    public void viewBooking(String bookingId) {

        for (Booking booking : bookings) {

            if (booking.getBookingId().equalsIgnoreCase(bookingId)) {

                System.out.println(booking);
                return;
            }
        }

        System.out.println("Booking not found.");
    }

    /*
     * View All Bookings
     */
    public void viewAllBookings() {

        if (bookings.isEmpty()) {

            System.out.println("\nNo bookings available.");
            return;
        }

        for (Booking booking : bookings) {

            System.out.println(booking);
        }
    }

    /*
     * Cancel Booking
     */
    public void cancelBooking(String bookingId) {

        for (Booking booking : bookings) {

            if (booking.getBookingId().equalsIgnoreCase(bookingId)) {

                if (booking.getBookingStatus() == BookingStatus.CANCELLED) {

                    System.out.println("Booking already cancelled.");
                    return;
                }

                booking.setBookingStatus(BookingStatus.CANCELLED);

                booking.getRoom().setStatus(RoomStatus.AVAILABLE);

                System.out.println("Booking cancelled successfully.");

                return;
            }
        }

        System.out.println("Booking not found.");
    }

    /*
     * Check-In
     */
    public void checkIn(String bookingId) {

        for (Booking booking : bookings) {

            if (booking.getBookingId().equalsIgnoreCase(bookingId)) {

                if (booking.getBookingStatus() != BookingStatus.CONFIRMED) {

                    System.out.println("Guest cannot check in.");
                    return;
                }

                booking.setBookingStatus(BookingStatus.CHECKED_IN);

                booking.getRoom().setStatus(RoomStatus.OCCUPIED);

                System.out.println("Guest checked in successfully.");

                return;
            }
        }

        System.out.println("Booking not found.");
    }

    /*
     * Check-Out
     */
    public void checkOut(String bookingId) {

        for (Booking booking : bookings) {

            if (booking.getBookingId().equalsIgnoreCase(bookingId)) {

                if (booking.getBookingStatus() != BookingStatus.CHECKED_IN) {

                    System.out.println("Guest has not checked in.");
                    return;
                }

                booking.setBookingStatus(BookingStatus.CHECKED_OUT);

                booking.getRoom().setStatus(RoomStatus.AVAILABLE);

                System.out.println("Guest checked out successfully.");

                printBill(booking);

                return;
            }
        }

        System.out.println("Booking not found.");
    }

    /*
     * Print Bill
     */
    public void printBill(Booking booking) {

        System.out.println("\n========== BILL ==========");
        System.out.println("Booking ID : " + booking.getBookingId());
        System.out.println("Guest      : " + booking.getGuest().getGuestName());
        System.out.println("Room ID    : " + booking.getRoom().getRoomId());
        System.out.println("Room Type  : " + booking.getRoom().getRoomType());

        BillCalculator.printBill(
                booking.getRoom().getPricePerNight(),
                booking.getCheckInDate(),
                booking.getCheckOutDate()
        );
    }

    /*
     * Booking History
     */
    public void displayBookingHistory() {
        FileManager.displayBookingHistory();
    }

    /*
     * Available Room Count
     */
    public int getAvailableRoomCount() {

        int count = 0;

        for (Room room : rooms) {

            if (room.getStatus() == RoomStatus.AVAILABLE) {
                count++;
            }
        }

        return count;
    }

    /*
     * Total Bookings
     */
    public int getTotalBookings() {
        return bookings.size();
    }

    /*
     * Hotel Summary
     */
    public void displayHotelSummary() {

        System.out.println("\n========== HOTEL SUMMARY ==========");
        System.out.println("Total Rooms      : " + rooms.size());
        System.out.println("Available Rooms  : " + getAvailableRoomCount());
        System.out.println("Total Bookings   : " + getTotalBookings());
        System.out.println("===================================");
    }

}