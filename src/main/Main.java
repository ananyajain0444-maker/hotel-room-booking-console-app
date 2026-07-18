package main;

import model.*;
import service.HotelService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final HotelService hotelService = new HotelService();

    public static void main(String[] args) {

        boolean exit = false;

        System.out.println("==========================================");
        System.out.println("   HOTEL ROOM BOOKING CONSOLE APPLICATION");
        System.out.println("==========================================");

        while (!exit) {

            displayMenu();

            System.out.print("\nEnter your choice: ");

            int choice;

            try {

                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Invalid input.");
                continue;
            }

            switch (choice) {

                case 1:
                    hotelService.displayAllRooms();
                    break;

                case 2:
                    hotelService.displayAvailableRooms();
                    break;

                case 3:
                    searchRooms();
                    break;

                case 4:
                    createBooking();
                    break;

                case 5:
                    viewBooking();
                    break;

                case 6:
                    hotelService.viewAllBookings();
                    break;

                case 7:
                    checkIn();
                    break;

                case 8:
                    checkOut();
                    break;

                case 9:
                    cancelBooking();
                    break;

                case 10:
                    hotelService.displayBookingHistory();
                    break;

                case 11:
                    hotelService.displayHotelSummary();
                    break;

                case 0:

                    exit = true;

                    System.out.println("\nThank you for using the system.");

                    break;

                default:

                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    /*
     * Menu
     */

    private static void displayMenu() {

        System.out.println("\n============== MENU ==============");

        System.out.println("1. Display All Rooms");
        System.out.println("2. Display Available Rooms");
        System.out.println("3. Search Rooms");
        System.out.println("4. Create Booking");
        System.out.println("5. View Booking");
        System.out.println("6. View All Bookings");
        System.out.println("7. Check In");
        System.out.println("8. Check Out");
        System.out.println("9. Cancel Booking");
        System.out.println("10. Booking History");
        System.out.println("11. Hotel Summary");
        System.out.println("0. Exit");

        System.out.println("==================================");
    }
    /*
     * Search Rooms
     */
    private static void searchRooms() {

        RoomType roomType = selectRoomType();

        List<Room> rooms = hotelService.searchRooms(roomType);

        if (rooms.isEmpty()) {

            System.out.println("\nNo rooms available.");
            return;
        }

        System.out.println("\nAvailable Rooms:");

        for (Room room : rooms) {

            System.out.println(room);
        }
    }

    /*
     * Create Booking
     */
    private static void createBooking() {

        try {

            System.out.print("\nGuest Name : ");
            String name = scanner.nextLine();

            System.out.print("Phone Number : ");
            String phone = scanner.nextLine();

            System.out.print("Email : ");
            String email = scanner.nextLine();

            System.out.print("ID Proof : ");
            String idProof = scanner.nextLine();

            Guest guest = new Guest(name, phone, email, idProof);

            RoomType roomType = selectRoomType();

            List<Room> availableRooms = hotelService.searchRooms(roomType);

            if (availableRooms.isEmpty()) {

                System.out.println("\nNo rooms available.");
                return;
            }

            System.out.println("\nAvailable Rooms");

            for (Room room : availableRooms) {

                System.out.println(room);
            }

            System.out.print("\nEnter Room ID : ");
            int roomId = Integer.parseInt(scanner.nextLine());

            System.out.print("Check-In Date (yyyy-mm-dd) : ");
            LocalDate checkIn = LocalDate.parse(scanner.nextLine());

            System.out.print("Check-Out Date (yyyy-mm-dd) : ");
            LocalDate checkOut = LocalDate.parse(scanner.nextLine());

            System.out.print("Number of Guests : ");
            int guests = Integer.parseInt(scanner.nextLine());

            Booking booking = hotelService.createBooking(
                    guest,
                    roomId,
                    checkIn,
                    checkOut,
                    guests
            );

            if (booking != null) {

                System.out.println("\nBooking Successful!\n");
                System.out.println(booking);
            }

        } catch (Exception e) {

            System.out.println("Invalid input.");
        }
    }

    /*
     * View Booking
     */
    private static void viewBooking() {

        System.out.print("\nEnter Booking ID : ");

        String bookingId = scanner.nextLine();

        hotelService.viewBooking(bookingId);
    }

    /*
     * Select Room Type
     */
    private static RoomType selectRoomType() {

        while (true) {

            System.out.println("\nChoose Room Type");

            System.out.println("1. Single");
            System.out.println("2. Double");
            System.out.println("3. Deluxe");
            System.out.println("4. Suite");

            System.out.print("Choice : ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    return RoomType.SINGLE;

                case 2:
                    return RoomType.DOUBLE;

                case 3:
                    return RoomType.DELUXE;

                case 4:
                    return RoomType.SUITE;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    /*
     * Check-In
     */
    private static void checkIn() {

        System.out.print("\nEnter Booking ID : ");

        String bookingId = scanner.nextLine();

        hotelService.checkIn(bookingId);
    }

    /*
     * Check-Out
     */
    private static void checkOut() {

        System.out.print("\nEnter Booking ID : ");

        String bookingId = scanner.nextLine();

        hotelService.checkOut(bookingId);
    }

    /*
     * Cancel Booking
     */
    private static void cancelBooking() {

        System.out.print("\nEnter Booking ID : ");

        String bookingId = scanner.nextLine();

        hotelService.cancelBooking(bookingId);
    }

}