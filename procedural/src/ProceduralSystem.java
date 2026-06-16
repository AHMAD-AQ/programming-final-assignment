package programmingfinalassignmentprocedural;

import java.util.Scanner;
import java.util.ArrayList;

// Procedural paradigm: all logic lives in static methods and main — no objects, no classes beyond this one
public class ProceduralSystem {

    // Counts how many students are registered for a specific activity by matching activity name
    static int countRegistrations(ArrayList<String[]> registrationList, String targetActivityName) {
        int count = 0;
        for (String[] registration : registrationList) {
            if (registration[2].equals(targetActivityName)) {
                count++;
            }
        }
        return count;
    }

    // Pauses the program and waits for the user to press Enter before returning to the menu
    static void waitForUser(Scanner scanner) {
        System.out.print("Press Enter to go back. ");
        scanner.nextLine();
    }

    // Notifies the user that no activities have been added to the system yet
    static void printNoActivitiesMessage() {
        System.out.println("No activities available. Please add an activity first.");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Each activity is stored as: [type, name, location, capacity, day, month, year, dateKey]
        ArrayList<String[]> activityList = new ArrayList<>();

        // Each registration is stored as: [studentName, gpa, activityName]
        ArrayList<String[]> registrationList = new ArrayList<>();

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

                int organizerChoice = 0;

                // Organizer menu loop — stays open until organizer selects Back
                while (organizerChoice != 4) {

                    System.out.println("=== Organizer Menu ===");
                    System.out.println("1. Add Activity");
                    System.out.println("2. Display Upcoming Activities by Date");
                    System.out.println("3. Check Activity Capacity");
                    System.out.println("4. Back");
                    System.out.print("Select an option: ");
                    organizerChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (organizerChoice == 1) {

                        // Collect all details needed to define a new activity
                        System.out.println("--- Add Activity ---");
                        System.out.println("1. Seminar");
                        System.out.println("2. Sports");
                        System.out.println("3. Workshop");
                        System.out.print("Select activity type: ");
                        int typeChoice = scanner.nextInt();
                        scanner.nextLine();

                        String activityType;

                        if (typeChoice == 1) {
                            activityType = "Seminar";
                        } else if (typeChoice == 2) {
                            activityType = "Sports";
                        } else if (typeChoice == 3) {
                            activityType = "Workshop";
                        } else {
                            System.out.println("Invalid type. Returning to menu.");
                            waitForUser(scanner);
                            continue;
                        }

                        System.out.print("Enter activity name: ");
                        String activityName = scanner.nextLine();

                        System.out.print("Enter activity location: ");
                        String activityLocation = scanner.nextLine();

                        System.out.print("Enter capacity: ");
                        int activityCapacity = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter day (DD): ");
                        String activityDay = scanner.nextLine();

                        System.out.print("Enter month (MM): ");
                        String activityMonth = scanner.nextLine();

                        System.out.print("Enter year (YYYY): ");
                        String activityYear = scanner.nextLine();

                        // dateKey format YYYYMMDD allows correct chronological string comparison in insertion sort
                        String dateKey = activityYear + activityMonth + activityDay;

                        activityList.add(new String[]{activityType, activityName, activityLocation, String.valueOf(activityCapacity), activityDay, activityMonth, activityYear, dateKey});
                        System.out.println("Activity added successfully.");
                        waitForUser(scanner);

                    } else if (organizerChoice == 2) {

                        if (activityList.isEmpty()) {
                            printNoActivitiesMessage();
                            waitForUser(scanner);
                            continue;
                        }

                        // Copy the list so the original insertion order is preserved
                        ArrayList<String[]> sortedActivityList = new ArrayList<>(activityList);

                        // Insertion sort — compares dateKey strings (YYYYMMDD) to sort chronologically
                        for (int i = 1; i < sortedActivityList.size(); i++) {
                            String[] currentActivity = sortedActivityList.get(i);
                            int j = i - 1;
                            while (j >= 0 && sortedActivityList.get(j)[7].compareTo(currentActivity[7]) > 0) {
                                sortedActivityList.set(j + 1, sortedActivityList.get(j));
                                j--;
                            }
                            sortedActivityList.set(j + 1, currentActivity);
                        }

                        System.out.println("--- Upcoming Activities (Earliest to Latest) ---");
                        for (String[] activity : sortedActivityList) {
                            int totalCapacity = Integer.parseInt(activity[3]);
                            int taken = countRegistrations(registrationList, activity[1]);
                            int spotsLeft = totalCapacity - taken;
                            System.out.println(activity[1] + " | " + activity[0] + " | " + activity[2] + " | " + activity[4] + "/" + activity[5] + "/" + activity[6] + " | Capacity: " + totalCapacity + " | Spots Left: " + spotsLeft);
                        }
                        waitForUser(scanner);

                    } else if (organizerChoice == 3) {

                        if (activityList.isEmpty()) {
                            printNoActivitiesMessage();
                            waitForUser(scanner);
                            continue;
                        }

                        // Shows total capacity alongside remaining spots to give a clear picture of demand
                        System.out.println("--- Activity Capacity ---");
                        for (String[] activity : activityList) {
                            int totalCapacity = Integer.parseInt(activity[3]);
                            int taken = countRegistrations(registrationList, activity[1]);
                            int spotsLeft = totalCapacity - taken;
                            System.out.println(activity[1] + " | Total: " + totalCapacity + " | Spots Left: " + spotsLeft);
                        }
                        waitForUser(scanner);

                    } else if (organizerChoice == 4) {
                        System.out.println("Returning to role menu...");

                    } else {
                        System.out.println("Invalid choice. Try again.");
                        waitForUser(scanner);
                    }
                }

            } else if (roleChoice == 2) {

                int studentChoice = 0;

                // Student menu loop — stays open until student selects Back
                while (studentChoice != 5) {

                    System.out.println("=== Student Menu ===");
                    System.out.println("1. Register for Activity");
                    System.out.println("2. Unregister from Activity");
                    System.out.println("3. Display Upcoming Activities by Date");
                    System.out.println("4. Check Activity Capacity");
                    System.out.println("5. Back");
                    System.out.print("Select an option: ");
                    studentChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (studentChoice == 1) {

                        // Guard clauses — check blockers before asking for any input
                        if (activityList.isEmpty()) {
                            printNoActivitiesMessage();
                            waitForUser(scanner);
                            continue;
                        }

                        // Check if every activity has reached its maximum capacity
                        boolean allActivitiesFull = true;
                        for (String[] activity : activityList) {
                            int totalCapacity = Integer.parseInt(activity[3]);
                            int spotsLeft = totalCapacity - countRegistrations(registrationList, activity[1]);
                            if (spotsLeft > 0) {
                                allActivitiesFull = false;
                                break;
                            }
                        }

                        if (allActivitiesFull) {
                            System.out.println("All activities are currently full. Cannot register.");
                            waitForUser(scanner);
                            continue;
                        }

                        System.out.println("--- Register for Activity ---");
                        System.out.print("Enter your name: ");
                        String studentName = scanner.nextLine();

                        System.out.print("Enter your GPA: ");
                        String studentGpa = scanner.nextLine();

                        // Display available activities with remaining spots
                        System.out.println("Available Activities:");
                        for (int i = 0; i < activityList.size(); i++) {
                            String[] activity = activityList.get(i);
                            int totalCapacity = Integer.parseInt(activity[3]);
                            int spotsLeft = totalCapacity - countRegistrations(registrationList, activity[1]);
                            System.out.println((i + 1) + ". " + activity[1] + " (" + activity[0] + ") - Spots left: " + spotsLeft);
                        }

                        System.out.print("Select activity number: ");
                        int activityChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (activityChoice < 1 || activityChoice > activityList.size()) {
                            System.out.println("Invalid choice. Returning to menu.");
                            waitForUser(scanner);
                            continue;
                        }

                        String[] selectedActivity = activityList.get(activityChoice - 1);
                        int totalCapacity = Integer.parseInt(selectedActivity[3]);
                        int spotsLeft = totalCapacity - countRegistrations(registrationList, selectedActivity[1]);

                        // Prevent overbooking — capacity is enforced at the point of registration
                        if (spotsLeft <= 0) {
                            System.out.println("Activity is full. Cannot register.");
                            waitForUser(scanner);
                            continue;
                        }

                        registrationList.add(new String[]{studentName, studentGpa, selectedActivity[1]});
                        System.out.println("Registered successfully.");
                        waitForUser(scanner);

                    } else if (studentChoice == 2) {

                        if (registrationList.isEmpty()) {
                            System.out.println("No registered students.");
                            waitForUser(scanner);
                            continue;
                        }

                        // Show all registrations as a numbered list for easy selection
                        System.out.println("--- Unregister from Activity ---");
                        for (int i = 0; i < registrationList.size(); i++) {
                            String[] registration = registrationList.get(i);
                            System.out.println((i + 1) + ". " + registration[0] + " - " + registration[2]);
                        }

                        System.out.print("Select your registration number to unregister: ");
                        int unregisterChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (unregisterChoice < 1 || unregisterChoice > registrationList.size()) {
                            System.out.println("Invalid choice. Returning to menu.");
                            waitForUser(scanner);
                            continue;
                        }

                        registrationList.remove(unregisterChoice - 1);
                        System.out.println("Unregistered successfully.");
                        waitForUser(scanner);

                    } else if (studentChoice == 3) {

                        if (activityList.isEmpty()) {
                            printNoActivitiesMessage();
                            waitForUser(scanner);
                            continue;
                        }

                        // Copy the list so the original insertion order is preserved
                        ArrayList<String[]> sortedActivityList = new ArrayList<>(activityList);

                        // Insertion sort — compares dateKey strings (YYYYMMDD) to sort chronologically
                        for (int i = 1; i < sortedActivityList.size(); i++) {
                            String[] currentActivity = sortedActivityList.get(i);
                            int j = i - 1;
                            while (j >= 0 && sortedActivityList.get(j)[7].compareTo(currentActivity[7]) > 0) {
                                sortedActivityList.set(j + 1, sortedActivityList.get(j));
                                j--;
                            }
                            sortedActivityList.set(j + 1, currentActivity);
                        }

                        System.out.println("--- Upcoming Activities (Earliest to Latest) ---");
                        for (String[] activity : sortedActivityList) {
                            int totalCapacity = Integer.parseInt(activity[3]);
                            int taken = countRegistrations(registrationList, activity[1]);
                            int spotsLeft = totalCapacity - taken;
                            System.out.println(activity[1] + " | " + activity[0] + " | " + activity[2] + " | " + activity[4] + "/" + activity[5] + "/" + activity[6] + " | Capacity: " + totalCapacity + " | Spots Left: " + spotsLeft);
                        }
                        waitForUser(scanner);

                    } else if (studentChoice == 4) {

                        if (activityList.isEmpty()) {
                            printNoActivitiesMessage();
                            waitForUser(scanner);
                            continue;
                        }

                        System.out.println("--- Activity Capacity ---");
                        for (String[] activity : activityList) {
                            int totalCapacity = Integer.parseInt(activity[3]);
                            int taken = countRegistrations(registrationList, activity[1]);
                            int spotsLeft = totalCapacity - taken;
                            System.out.println(activity[1] + " | Total: " + totalCapacity + " | Spots Left: " + spotsLeft);
                        }
                        waitForUser(scanner);

                    } else if (studentChoice == 5) {
                        System.out.println("Returning to role menu...");

                    } else {
                        System.out.println("Invalid choice. Try again.");
                        waitForUser(scanner);
                    }
                }

            } else if (roleChoice == 3) {

                int universityChoice = 0;

                // University menu loop — stays open until university selects Back
                while (universityChoice != 7) {

                    System.out.println("=== University Menu ===");
                    System.out.println("1. Add Activity");
                    System.out.println("2. Register Student for Activity");
                    System.out.println("3. Unregister Student from Activity");
                    System.out.println("4. Display Upcoming Activities by Date");
                    System.out.println("5. Check Activity Capacity");
                    System.out.println("6. Display All Registered Participants");
                    System.out.println("7. Back");
                    System.out.print("Select an option: ");
                    universityChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (universityChoice == 1) {

                        // Collect all details needed to define a new activity
                        System.out.println("--- Add Activity ---");
                        System.out.println("1. Seminar");
                        System.out.println("2. Sports");
                        System.out.println("3. Workshop");
                        System.out.print("Select activity type: ");
                        int typeChoice = scanner.nextInt();
                        scanner.nextLine();

                        String activityType;

                        if (typeChoice == 1) {
                            activityType = "Seminar";
                        } else if (typeChoice == 2) {
                            activityType = "Sports";
                        } else if (typeChoice == 3) {
                            activityType = "Workshop";
                        } else {
                            System.out.println("Invalid type. Returning to menu.");
                            waitForUser(scanner);
                            continue;
                        }

                        System.out.print("Enter activity name: ");
                        String activityName = scanner.nextLine();

                        System.out.print("Enter activity location: ");
                        String activityLocation = scanner.nextLine();

                        System.out.print("Enter capacity: ");
                        int activityCapacity = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter day (DD): ");
                        String activityDay = scanner.nextLine();

                        System.out.print("Enter month (MM): ");
                        String activityMonth = scanner.nextLine();

                        System.out.print("Enter year (YYYY): ");
                        String activityYear = scanner.nextLine();

                        // dateKey format YYYYMMDD allows correct chronological string comparison in insertion sort
                        String dateKey = activityYear + activityMonth + activityDay;

                        activityList.add(new String[]{activityType, activityName, activityLocation, String.valueOf(activityCapacity), activityDay, activityMonth, activityYear, dateKey});
                        System.out.println("Activity added successfully.");
                        waitForUser(scanner);

                    } else if (universityChoice == 2) {

                        // Guard clauses — check blockers before asking for any input
                        if (activityList.isEmpty()) {
                            printNoActivitiesMessage();
                            waitForUser(scanner);
                            continue;
                        }

                        // Check if every activity has reached its maximum capacity
                        boolean allActivitiesFull = true;
                        for (String[] activity : activityList) {
                            int totalCapacity = Integer.parseInt(activity[3]);
                            int spotsLeft = totalCapacity - countRegistrations(registrationList, activity[1]);
                            if (spotsLeft > 0) {
                                allActivitiesFull = false;
                                break;
                            }
                        }

                        if (allActivitiesFull) {
                            System.out.println("All activities are currently full. Cannot register any students.");
                            waitForUser(scanner);
                            continue;
                        }

                        System.out.println("--- Register Student ---");
                        System.out.print("Enter student name: ");
                        String studentName = scanner.nextLine();

                        System.out.print("Enter student GPA: ");
                        String studentGpa = scanner.nextLine();

                        // Display available activities with remaining spots so university can make an informed choice
                        System.out.println("Available Activities:");
                        for (int i = 0; i < activityList.size(); i++) {
                            String[] activity = activityList.get(i);
                            int totalCapacity = Integer.parseInt(activity[3]);
                            int spotsLeft = totalCapacity - countRegistrations(registrationList, activity[1]);
                            System.out.println((i + 1) + ". " + activity[1] + " (" + activity[0] + ") - Spots left: " + spotsLeft);
                        }

                        System.out.print("Select activity number: ");
                        int activityChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (activityChoice < 1 || activityChoice > activityList.size()) {
                            System.out.println("Invalid choice. Returning to menu.");
                            waitForUser(scanner);
                            continue;
                        }

                        String[] selectedActivity = activityList.get(activityChoice - 1);
                        int totalCapacity = Integer.parseInt(selectedActivity[3]);
                        int spotsLeft = totalCapacity - countRegistrations(registrationList, selectedActivity[1]);

                        // Prevent overbooking — capacity is enforced at the point of registration
                        if (spotsLeft <= 0) {
                            System.out.println("Activity is full. Cannot register student.");
                            waitForUser(scanner);
                            continue;
                        }

                        registrationList.add(new String[]{studentName, studentGpa, selectedActivity[1]});
                        System.out.println("Student registered successfully.");
                        waitForUser(scanner);

                    } else if (universityChoice == 3) {

                        if (registrationList.isEmpty()) {
                            System.out.println("No registered students.");
                            waitForUser(scanner);
                            continue;
                        }

                        // Show all registrations as a numbered list for easy selection
                        System.out.println("--- Unregister Student ---");
                        for (int i = 0; i < registrationList.size(); i++) {
                            String[] registration = registrationList.get(i);
                            System.out.println((i + 1) + ". " + registration[0] + " - " + registration[2]);
                        }

                        System.out.print("Select student number to unregister: ");
                        int unregisterChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (unregisterChoice < 1 || unregisterChoice > registrationList.size()) {
                            System.out.println("Invalid choice. Returning to menu.");
                            waitForUser(scanner);
                            continue;
                        }

                        registrationList.remove(unregisterChoice - 1);
                        System.out.println("Student unregistered successfully.");
                        waitForUser(scanner);

                    } else if (universityChoice == 4) {

                        if (activityList.isEmpty()) {
                            printNoActivitiesMessage();
                            waitForUser(scanner);
                            continue;
                        }

                        // Copy the list so the original insertion order is preserved
                        ArrayList<String[]> sortedActivityList = new ArrayList<>(activityList);

                        // Insertion sort — compares dateKey strings (YYYYMMDD) to sort chronologically
                        for (int i = 1; i < sortedActivityList.size(); i++) {
                            String[] currentActivity = sortedActivityList.get(i);
                            int j = i - 1;
                            while (j >= 0 && sortedActivityList.get(j)[7].compareTo(currentActivity[7]) > 0) {
                                sortedActivityList.set(j + 1, sortedActivityList.get(j));
                                j--;
                            }
                            sortedActivityList.set(j + 1, currentActivity);
                        }

                        System.out.println("--- Upcoming Activities (Earliest to Latest) ---");
                        for (String[] activity : sortedActivityList) {
                            int totalCapacity = Integer.parseInt(activity[3]);
                            int taken = countRegistrations(registrationList, activity[1]);
                            int spotsLeft = totalCapacity - taken;
                            System.out.println(activity[1] + " | " + activity[0] + " | " + activity[2] + " | " + activity[4] + "/" + activity[5] + "/" + activity[6] + " | Capacity: " + totalCapacity + " | Spots Left: " + spotsLeft);
                        }
                        waitForUser(scanner);

                    } else if (universityChoice == 5) {

                        if (activityList.isEmpty()) {
                            printNoActivitiesMessage();
                            waitForUser(scanner);
                            continue;
                        }

                        // Shows total capacity alongside remaining spots to give a clear picture of demand
                        System.out.println("--- Activity Capacity ---");
                        for (String[] activity : activityList) {
                            int totalCapacity = Integer.parseInt(activity[3]);
                            int taken = countRegistrations(registrationList, activity[1]);
                            int spotsLeft = totalCapacity - taken;
                            System.out.println(activity[1] + " | Total: " + totalCapacity + " | Spots Left: " + spotsLeft);
                        }
                        waitForUser(scanner);

                    } else if (universityChoice == 6) {

                        if (registrationList.isEmpty()) {
                            System.out.println("No registered students.");
                            waitForUser(scanner);
                            continue;
                        }

                        // Displays all registered participants across all activities — tracks university engagement
                        System.out.println("--- All Registered Participants ---");
                        for (String[] activity : activityList) {
                            System.out.println(activity[1] + " (" + activity[0] + "):");
                            boolean hasParticipants = false;
                            for (String[] registration : registrationList) {
                                if (registration[2].equals(activity[1])) {
                                    System.out.println("   - " + registration[0] + " | GPA: " + registration[1]);
                                    hasParticipants = true;
                                }
                            }
                            if (!hasParticipants) {
                                System.out.println("   No participants registered.");
                            }
                        }
                        waitForUser(scanner);

                    } else if (universityChoice == 7) {
                        System.out.println("Returning to role menu...");

                    } else {
                        System.out.println("Invalid choice. Try again.");
                        waitForUser(scanner);
                    }
                }

            } else if (roleChoice == 4) {
                System.out.println("Goodbye!");

            } else {
                System.out.println("Invalid choice. Try again.");
                waitForUser(scanner);
            }
        }

        scanner.close();
    }
}
