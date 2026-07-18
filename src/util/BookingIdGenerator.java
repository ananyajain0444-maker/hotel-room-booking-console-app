package util;

public class BookingIdGenerator {

    // Starting booking number
    private static int counter = 1001;

    /**
     * Generates a unique booking ID.
     * Example:
     * BK1001
     * BK1002
     * BK1003
     */
    public static String generateBookingId() {

        return "BK" + counter++;
    }

    /**
     * Returns the next booking number
     * without generating a new ID.
     */
    public static int getCurrentCounter() {

        return counter;
    }

    /**
     * Sets the booking counter.
     * Useful when loading previous
     * booking records from a file.
     */
    public static void setCounter(int counter) {

        BookingIdGenerator.counter = counter;
    }

    /**
     * Resets booking IDs.
     * Mainly used for testing.
     */
    public static void resetCounter() {

        counter = 1001;
    }

}