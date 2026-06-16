package programmingfinalassignmentoop;

import java.util.ArrayList;
import java.util.Scanner;

// Manages all system logic — owns the activity and student lists and handles all operations
public class BookingSystem {

    // ArrayList of Activity objects — stores all activity types polymorphically under the parent class
    private ArrayList<Activity> activityList;

    // ArrayList of Student objects — each student holds a reference to their registered activity
    private ArrayList<Student> studentList;

    // Constructor — initializes both lists as empty ArrayLists
    public BookingSystem() {
        this.activityList = new ArrayList<>();
        this.studentList = new ArrayList<>();
    }

    // Pauses the program and waits for the user to press Enter before returning to the menu
    public void waitForUser(Scanner scanner) {
        System.out.print("Press Enter to go back. ");
        scanner.nextLine();
    }

    // Notifies the user that no activities have been added to the system yet
    public void printNoActivitiesMessage() {
        System.out.println("No activities available. Please add an activity first.");
    }

    // Counts how many students are registered for a specific activity by matching activity name
    public int countRegistrations(String targetActivityName) {
        int count = 0;
        for (Student student : studentList) {
            if (student.getRegisteredActivity().getActivityName().equals(targetActivityName)) {
                count++;
            }
        }
        return count;
    }

    // Adds a new activity to the system based on the type choice provided
    public void addActivity(Scanner scanner) {
        System.out.println("--- Add Activity ---");
        System.out.println("1. Seminar");
        System.out.println("2. Sports");
        System.out.println("3. Workshop");
        System.out.print("Select activity type: ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        if (typeChoice < 1 || typeChoice > 3) {
            System.out.println("Invalid type. Returning to menu.");
            waitForUser(scanner);
            return;
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

        // Create the correct subclass object based on the type choice — polymorphism in action
        if (typeChoice == 1) {
            activityList.add(new Seminar(activityName, activityLocation, activityCapacity, activityDay, activityMonth, activityYear));
        } else if (typeChoice == 2) {
            activityList.add(new Sports(activityName, activityLocation, activityCapacity, activityDay, activityMonth, activityYear));
        } else if (typeChoice == 3) {
            activityList.add(new Workshop(activityName, activityLocation, activityCapacity, activityDay, activityMonth, activityYear));
        }

        System.out.println("Activity added successfully.");
        waitForUser(scanner);
    }

    // Registers a student to an available activity after checking capacity
    public void registerStudent(Scanner scanner) {

        // Guard clauses — check blockers before asking for any input
        if (activityList.isEmpty()) {
            printNoActivitiesMessage();
            waitForUser(scanner);
            return;
        }

        // Check if every activity has reached its maximum capacity
        boolean allActivitiesFull = true;
        for (Activity activity : activityList) {
            int spotsLeft = activity.getActivityCapacity() - countRegistrations(activity.getActivityName());
            if (spotsLeft > 0) {
                allActivitiesFull = false;
                break;
            }
        }

        if (allActivitiesFull) {
            System.out.println("All activities are currently full. Cannot register any students.");
            waitForUser(scanner);
            return;
        }

        System.out.println("--- Register Student ---");
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();

        System.out.print("Enter student GPA: ");
        String studentGpa = scanner.nextLine();

        // Display available activities with remaining spots so the user can make an informed choice
        System.out.println("Available Activities:");
        for (int i = 0; i < activityList.size(); i++) {
            Activity activity = activityList.get(i);
            int spotsLeft = activity.getActivityCapacity() - countRegistrations(activity.getActivityName());
            System.out.println((i + 1) + ". " + activity.getActivityName() + " (" + activity.getActivityType() + ") - Spots left: " + spotsLeft);
        }

        System.out.print("Select activity number: ");
        int activityChoice = scanner.nextInt();
        scanner.nextLine();

        if (activityChoice < 1 || activityChoice > activityList.size()) {
            System.out.println("Invalid choice. Returning to menu.");
            waitForUser(scanner);
            return;
        }

        Activity selectedActivity = activityList.get(activityChoice - 1);
        int spotsLeft = selectedActivity.getActivityCapacity() - countRegistrations(selectedActivity.getActivityName());

        // Prevent overbooking — capacity is enforced at the point of registration
        if (spotsLeft <= 0) {
            System.out.println("Activity is full. Cannot register student.");
            waitForUser(scanner);
            return;
        }

        studentList.add(new Student(studentName, studentGpa, selectedActivity));
        System.out.println("Student registered successfully.");
        waitForUser(scanner);
    }

    // Removes a student registration from the system
    public void unregisterStudent(Scanner scanner) {

        if (studentList.isEmpty()) {
            System.out.println("No registered students.");
            waitForUser(scanner);
            return;
        }

        // Show all registrations as a numbered list for easy selection
        System.out.println("--- Unregister Student ---");
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            System.out.println((i + 1) + ". " + student.getStudentName() + " - " + student.getRegisteredActivity().getActivityName());
        }

        System.out.print("Select student number to unregister: ");
        int unregisterChoice = scanner.nextInt();
        scanner.nextLine();

        if (unregisterChoice < 1 || unregisterChoice > studentList.size()) {
            System.out.println("Invalid choice. Returning to menu.");
            waitForUser(scanner);
            return;
        }

        studentList.remove(unregisterChoice - 1);
        System.out.println("Student unregistered successfully.");
        waitForUser(scanner);
    }

    // Displays all activities sorted by date from earliest to latest using insertion sort
    public void displayActivitiesByDate(Scanner scanner) {

        if (activityList.isEmpty()) {
            printNoActivitiesMessage();
            waitForUser(scanner);
            return;
        }

        // Copy the list so the original insertion order is preserved
        ArrayList<Activity> sortedActivityList = new ArrayList<>(activityList);

        // Insertion sort — compares dateKey strings (YYYYMMDD) to sort chronologically
        for (int i = 1; i < sortedActivityList.size(); i++) {
            Activity currentActivity = sortedActivityList.get(i);
            int j = i - 1;
            while (j >= 0 && sortedActivityList.get(j).getDateKey().compareTo(currentActivity.getDateKey()) > 0) {
                sortedActivityList.set(j + 1, sortedActivityList.get(j));
                j--;
            }
            sortedActivityList.set(j + 1, currentActivity);
        }

        System.out.println("--- Upcoming Activities (Earliest to Latest) ---");
        for (Activity activity : sortedActivityList) {
            int spotsLeft = activity.getActivityCapacity() - countRegistrations(activity.getActivityName());
            System.out.println(activity.getActivityName() + " | " + activity.getActivityType() + " | " + activity.getActivityLocation() + " | " + activity.getActivityDay() + "/" + activity.getActivityMonth() + "/" + activity.getActivityYear() + " | Capacity: " + activity.getActivityCapacity() + " | Spots Left: " + spotsLeft);
        }
        waitForUser(scanner);
    }

    // Displays total capacity and remaining spots for each activity
    public void checkActivityCapacity(Scanner scanner) {

        if (activityList.isEmpty()) {
            printNoActivitiesMessage();
            waitForUser(scanner);
            return;
        }

        // Shows total capacity alongside remaining spots to give a clear picture of demand
        System.out.println("--- Activity Capacity ---");
        for (Activity activity : activityList) {
            int spotsLeft = activity.getActivityCapacity() - countRegistrations(activity.getActivityName());
            System.out.println(activity.getActivityName() + " | Total: " + activity.getActivityCapacity() + " | Spots Left: " + spotsLeft);
        }
        waitForUser(scanner);
    }

    // Displays all registered participants grouped by activity — tracks university engagement
    public void displayAllParticipants(Scanner scanner) {

        if (studentList.isEmpty()) {
            System.out.println("No registered students.");
            waitForUser(scanner);
            return;
        }

        // Displays all registered participants across all activities — tracks university engagement
        System.out.println("--- All Registered Participants ---");
        for (Activity activity : activityList) {
            System.out.println(activity.getActivityName() + " (" + activity.getActivityType() + "):");
            boolean hasParticipants = false;
            for (Student student : studentList) {
                if (student.getRegisteredActivity().getActivityName().equals(activity.getActivityName())) {
                    System.out.println("   - " + student.getStudentName() + " | GPA: " + student.getStudentGpa());
                    hasParticipants = true;
                }
            }
            if (!hasParticipants) {
                System.out.println("   No participants registered.");
            }
        }
        waitForUser(scanner);
    }

    // Runs the organizer menu loop — add activities, display by date, check capacity
    public void runOrganizerMenu(Scanner scanner) {
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
                addActivity(scanner);
            } else if (organizerChoice == 2) {
                displayActivitiesByDate(scanner);
            } else if (organizerChoice == 3) {
                checkActivityCapacity(scanner);
            } else if (organizerChoice == 4) {
                System.out.println("Returning to role menu...");
            } else {
                System.out.println("Invalid choice. Try again.");
                waitForUser(scanner);
            }
        }
    }

    // Runs the student menu loop — register, unregister, display activities, check capacity
    public void runStudentMenu(Scanner scanner) {
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
                registerStudent(scanner);
            } else if (studentChoice == 2) {
                unregisterStudent(scanner);
            } else if (studentChoice == 3) {
                displayActivitiesByDate(scanner);
            } else if (studentChoice == 4) {
                checkActivityCapacity(scanner);
            } else if (studentChoice == 5) {
                System.out.println("Returning to role menu...");
            } else {
                System.out.println("Invalid choice. Try again.");
                waitForUser(scanner);
            }
        }
    }

    // Runs the university menu loop — full access to all features
    public void runUniversityMenu(Scanner scanner) {
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
                addActivity(scanner);
            } else if (universityChoice == 2) {
                registerStudent(scanner);
            } else if (universityChoice == 3) {
                unregisterStudent(scanner);
            } else if (universityChoice == 4) {
                displayActivitiesByDate(scanner);
            } else if (universityChoice == 5) {
                checkActivityCapacity(scanner);
            } else if (universityChoice == 6) {
                displayAllParticipants(scanner);
            } else if (universityChoice == 7) {
                System.out.println("Returning to role menu...");
            } else {
                System.out.println("Invalid choice. Try again.");
                waitForUser(scanner);
            }
        }
    }

    // Returns the activity list for direct access by the event-driven layer
    public ArrayList<Activity> getActivityList() {
        return activityList;
    }

    // Returns the student list for direct access by the event-driven layer
    public ArrayList<Student> getStudentList() {
        return studentList;
    }
}