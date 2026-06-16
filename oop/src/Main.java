package programmingfinalassignmentoop;

import java.util.Scanner;

// OOP paradigm entry point — creates objects and delegates all logic to BookingSystem
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // BookingSystem object owns all data and logic — main just runs the role menu
        BookingSystem bookingSystem = new BookingSystem();

        int roleChoice = 0;

        // Main loop — keeps the program running until the user chooses to quit from the role menu
        while (roleChoice != 4) {
            System.out.println("=== Smart Campus Activity Booking System ===");
            System.out.println("1. Organizer");
            System.out.println("2. Student");
            System.out.println("3. University");
            System.out.println("4. Quit");
            System.out.print("Select your role: ");
            roleChoice = scanner.nextInt();
            scanner.nextLine();

            if (roleChoice == 1) {
                bookingSystem.runOrganizerMenu(scanner);
            } else if (roleChoice == 2) {
                bookingSystem.runStudentMenu(scanner);
            } else if (roleChoice == 3) {
                bookingSystem.runUniversityMenu(scanner);
            } else if (roleChoice == 4) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice. Try again.");
                bookingSystem.waitForUser(scanner);
            }
        }

        scanner.close();
    }
}
