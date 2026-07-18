package util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BillCalculator {

    // GST Rate (10%)
    private static final double GST_RATE = 0.10;

    /**
     * Calculate number of nights between
     * check-in and check-out.
     */
    public static int calculateNights(LocalDate checkIn,
                                      LocalDate checkOut) {

        return (int) ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    /**
     * Calculate room charge without GST.
     */
    public static double calculateRoomCharge(double pricePerNight,
                                             int nights) {

        return pricePerNight * nights;
    }

    /**
     * Calculate GST amount.
     */
    public static double calculateGST(double roomCharge) {

        return roomCharge * GST_RATE;
    }

    /**
     * Calculate final bill.
     */
    public static double calculateTotalBill(double pricePerNight,
                                            LocalDate checkIn,
                                            LocalDate checkOut) {

        int nights = calculateNights(checkIn, checkOut);

        double roomCharge = calculateRoomCharge(pricePerNight, nights);

        double gst = calculateGST(roomCharge);

        return roomCharge + gst;
    }

    /**
     * Display formatted bill.
     */
    public static void printBill(double pricePerNight,
                                 LocalDate checkIn,
                                 LocalDate checkOut) {

        int nights = calculateNights(checkIn, checkOut);

        double roomCharge = calculateRoomCharge(pricePerNight, nights);

        double gst = calculateGST(roomCharge);

        double total = roomCharge + gst;

        System.out.println("\n========== BILL SUMMARY ==========");
        System.out.println("Check-In Date    : " + checkIn);
        System.out.println("Check-Out Date   : " + checkOut);
        System.out.println("Number of Nights : " + nights);

        System.out.printf("Room Charge      : Rs. %.2f%n", roomCharge);
        System.out.printf("GST (10%%)        : Rs. %.2f%n", gst);

        System.out.println("----------------------------------");

        System.out.printf("Total Bill       : Rs. %.2f%n", total);

        System.out.println("==================================");
    }
}