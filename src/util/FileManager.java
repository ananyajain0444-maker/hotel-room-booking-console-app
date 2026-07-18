package util;

import model.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    // File where booking records are stored
    private static final String FILE_PATH = "data/bookings.txt";

    /**
     * Saves a booking to the text file.
     */
    public static void saveBooking(Booking booking) {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_PATH, true))) {

            writer.write(booking.getBookingId() + ",");
            writer.write(booking.getGuest().getGuestName() + ",");
            writer.write(booking.getGuest().getPhoneNumber() + ",");
            writer.write(booking.getGuest().getEmail() + ",");
            writer.write(booking.getRoom().getRoomId() + ",");
            writer.write(booking.getRoom().getRoomType().toString() + ",");
            writer.write(booking.getCheckInDate().toString() + ",");
            writer.write(booking.getCheckOutDate().toString() + ",");
            writer.write(String.valueOf(booking.getNumberOfGuests()) + ",");
            writer.write(String.valueOf(booking.getNumberOfNights()) + ",");
            writer.write(String.format("%.2f", booking.getTotalAmount()) + ",");
            writer.write(booking.getBookingStatus().toString());

            writer.newLine();

        } catch (IOException e) {

            System.out.println("Error saving booking data.");
            e.printStackTrace();
        }
    }

    /**
     * Displays all booking records.
     */
    public static void displayBookingHistory() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {

            System.out.println("\nNo booking history found.");
            return;
        }

        System.out.println("\n========== BOOKING HISTORY ==========");

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                System.out.println(line);
            }

        } catch (IOException e) {

            System.out.println("Error reading booking history.");
        }

        System.out.println("=====================================");
    }

    /**
     * Reads all booking records.
     */
    public static List<String> loadBookings() {

        List<String> bookings = new ArrayList<>();

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return bookings;
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                bookings.add(line);
            }

        } catch (IOException e) {

            System.out.println("Error loading bookings.");
        }

        return bookings;
    }

    /**
     * Clears booking history.
     * Useful for testing.
     */
    public static void clearBookingHistory() {

        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {

            writer.print("");

        } catch (FileNotFoundException e) {

            System.out.println("Unable to clear booking history.");
        }
    }

}