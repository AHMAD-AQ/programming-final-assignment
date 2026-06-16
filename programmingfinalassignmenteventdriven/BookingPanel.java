package programmingfinalassignmenteventdriven;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

// Handles all organizer, student, and university interactions through button click events
// Builds on top of the OOP classes — reuses BookingSystem, Activity, Student directly
public class BookingPanel extends JPanel {

    private BookingSystem bookingSystem;
    private int role;
    private MainFrame mainFrame;

    // Output area — displays all system responses to user actions
    private JTextArea outputArea;

    // Constructor — builds the panel with the correct buttons based on role
    // Role 1 = Organizer, Role 2 = Student, Role 3 = University
    public BookingPanel(BookingSystem bookingSystem, int role, MainFrame mainFrame) {
        this.bookingSystem = bookingSystem;
        this.role = role;
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());

        // Title label at the top
        String title;
        if (role == 1) {
            title = "=== Organizer Menu ===";
        } else if (role == 2) {
            title = "=== Student Menu ===";
        } else {
            title = "=== University Menu ===";
        }

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Output area — shows results of every action
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // Button panel on the right side
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        if (role == 1) {
            buildOrganizerButtons(buttonPanel);
        } else if (role == 2) {
            buildStudentButtons(buttonPanel);
        } else {
            buildUniversityButtons(buttonPanel);
        }

        add(buttonPanel, BorderLayout.EAST);
    }

    // Builds organizer-specific buttons and attaches their action listeners
    private void buildOrganizerButtons(JPanel buttonPanel) {

        JButton addActivityButton = new JButton("Add Activity");
        JButton displayActivitiesButton = new JButton("Display Upcoming Activities");
        JButton checkCapacityButton = new JButton("Check Activity Capacity");
        JButton backButton = new JButton("Back");

        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        addActivityButton.setFont(buttonFont);
        displayActivitiesButton.setFont(buttonFont);
        checkCapacityButton.setFont(buttonFont);
        backButton.setFont(buttonFont);

        buttonPanel.add(addActivityButton);
        buttonPanel.add(displayActivitiesButton);
        buttonPanel.add(checkCapacityButton);
        buttonPanel.add(backButton);

        // Each button click fires an event handled by its ActionListener
        addActivityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddActivity();
            }
        });

        displayActivitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDisplayActivities();
            }
        });

        checkCapacityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckCapacity();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showRolePanel();
            }
        });
    }

    // Builds student-specific buttons and attaches their action listeners
    private void buildStudentButtons(JPanel buttonPanel) {

        JButton registerButton = new JButton("Register for Activity");
        JButton unregisterButton = new JButton("Unregister from Activity");
        JButton displayActivitiesButton = new JButton("Display Upcoming Activities");
        JButton checkCapacityButton = new JButton("Check Activity Capacity");
        JButton backButton = new JButton("Back");

        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        registerButton.setFont(buttonFont);
        unregisterButton.setFont(buttonFont);
        displayActivitiesButton.setFont(buttonFont);
        checkCapacityButton.setFont(buttonFont);
        backButton.setFont(buttonFont);

        buttonPanel.add(registerButton);
        buttonPanel.add(unregisterButton);
        buttonPanel.add(displayActivitiesButton);
        buttonPanel.add(checkCapacityButton);
        buttonPanel.add(backButton);

        // Each button click fires an event handled by its ActionListener
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegisterStudent();
            }
        });

        unregisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUnregisterStudent();
            }
        });

        displayActivitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDisplayActivities();
            }
        });

        checkCapacityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckCapacity();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showRolePanel();
            }
        });
    }

    // Builds university-specific buttons and attaches their action listeners
    private void buildUniversityButtons(JPanel buttonPanel) {

        JButton addActivityButton = new JButton("Add Activity");
        JButton registerStudentButton = new JButton("Register Student");
        JButton unregisterStudentButton = new JButton("Unregister Student");
        JButton displayActivitiesButton = new JButton("Display Upcoming Activities");
        JButton checkCapacityButton = new JButton("Check Activity Capacity");
        JButton displayParticipantsButton = new JButton("Display All Participants");
        JButton backButton = new JButton("Back");

        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        addActivityButton.setFont(buttonFont);
        registerStudentButton.setFont(buttonFont);
        unregisterStudentButton.setFont(buttonFont);
        displayActivitiesButton.setFont(buttonFont);
        checkCapacityButton.setFont(buttonFont);
        displayParticipantsButton.setFont(buttonFont);
        backButton.setFont(buttonFont);

        buttonPanel.add(addActivityButton);
        buttonPanel.add(registerStudentButton);
        buttonPanel.add(unregisterStudentButton);
        buttonPanel.add(displayActivitiesButton);
        buttonPanel.add(checkCapacityButton);
        buttonPanel.add(displayParticipantsButton);
        buttonPanel.add(backButton);

        // Each button click fires an event handled by its ActionListener
        addActivityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddActivity();
            }
        });

        registerStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegisterStudent();
            }
        });

        unregisterStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUnregisterStudent();
            }
        });

        displayActivitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDisplayActivities();
            }
        });

        checkCapacityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckCapacity();
            }
        });

        displayParticipantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDisplayParticipants();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showRolePanel();
            }
        });
    }

    // Handles add activity event — collects input via dialogs and adds to system
    private void handleAddActivity() {
        String[] types = {"Seminar", "Sports", "Workshop"};
        String activityType = (String) JOptionPane.showInputDialog(
                this,
                "Select activity type:",
                "Add Activity",
                JOptionPane.PLAIN_MESSAGE,
                null,
                types,
                types[0]
        );

        if (activityType == null) {
            return;
        }

        String activityName = JOptionPane.showInputDialog(this, "Enter activity name:");
        if (activityName == null || activityName.trim().isEmpty()) {
            return;
        }

        String activityLocation = JOptionPane.showInputDialog(this, "Enter activity location:");
        if (activityLocation == null || activityLocation.trim().isEmpty()) {
            return;
        }

        String capacityInput = JOptionPane.showInputDialog(this, "Enter capacity:");
        if (capacityInput == null || capacityInput.trim().isEmpty()) {
            return;
        }

        int activityCapacity = Integer.parseInt(capacityInput);

        String activityDay = JOptionPane.showInputDialog(this, "Enter day (DD):");
        if (activityDay == null || activityDay.trim().isEmpty()) {
            return;
        }

        String activityMonth = JOptionPane.showInputDialog(this, "Enter month (MM):");
        if (activityMonth == null || activityMonth.trim().isEmpty()) {
            return;
        }

        String activityYear = JOptionPane.showInputDialog(this, "Enter year (YYYY):");
        if (activityYear == null || activityYear.trim().isEmpty()) {
            return;
        }

        // Create the correct subclass object based on the selected type
        if (activityType.equals("Seminar")) {
            bookingSystem.getActivityList().add(new Seminar(activityName, activityLocation, activityCapacity, activityDay, activityMonth, activityYear));
        } else if (activityType.equals("Sports")) {
            bookingSystem.getActivityList().add(new Sports(activityName, activityLocation, activityCapacity, activityDay, activityMonth, activityYear));
        } else if (activityType.equals("Workshop")) {
            bookingSystem.getActivityList().add(new Workshop(activityName, activityLocation, activityCapacity, activityDay, activityMonth, activityYear));
        }

        outputArea.setText("Activity added successfully:\n" + activityName + " | " + activityType + " | " + activityLocation + " | " + activityDay + "/" + activityMonth + "/" + activityYear + " | Capacity: " + activityCapacity);
    }

    // Handles register student event — collects input and registers student to selected activity
    private void handleRegisterStudent() {

        if (bookingSystem.getActivityList().isEmpty()) {
            outputArea.setText("No activities available. Please add an activity first.");
            return;
        }

        // Check if every activity has reached its maximum capacity
        boolean allActivitiesFull = true;
        for (Activity activity : bookingSystem.getActivityList()) {
            int spotsLeft = activity.getActivityCapacity() - bookingSystem.countRegistrations(activity.getActivityName());
            if (spotsLeft > 0) {
                allActivitiesFull = false;
                break;
            }
        }

        if (allActivitiesFull) {
            outputArea.setText("All activities are currently full. Cannot register any students.");
            return;
        }

        String studentName = JOptionPane.showInputDialog(this, "Enter student name:");
        if (studentName == null || studentName.trim().isEmpty()) {
            return;
        }

        String studentGpa = JOptionPane.showInputDialog(this, "Enter student GPA:");
        if (studentGpa == null || studentGpa.trim().isEmpty()) {
            return;
        }

        // Build activity options showing spots left for each
        ArrayList<Activity> activityList = bookingSystem.getActivityList();
        String[] activityOptions = new String[activityList.size()];
        for (int i = 0; i < activityList.size(); i++) {
            Activity activity = activityList.get(i);
            int spotsLeft = activity.getActivityCapacity() - bookingSystem.countRegistrations(activity.getActivityName());
            activityOptions[i] = activity.getActivityName() + " (" + activity.getActivityType() + ") - Spots left: " + spotsLeft;
        }

        String selectedOption = (String) JOptionPane.showInputDialog(
                this,
                "Select activity:",
                "Register Student",
                JOptionPane.PLAIN_MESSAGE,
                null,
                activityOptions,
                activityOptions[0]
        );

        if (selectedOption == null) {
            return;
        }

        // Find the selected activity by matching the chosen option string
        int selectedIndex = 0;
        for (int i = 0; i < activityOptions.length; i++) {
            if (activityOptions[i].equals(selectedOption)) {
                selectedIndex = i;
                break;
            }
        }

        Activity selectedActivity = activityList.get(selectedIndex);
        int spotsLeft = selectedActivity.getActivityCapacity() - bookingSystem.countRegistrations(selectedActivity.getActivityName());

        // Prevent overbooking — capacity is enforced at the point of registration
        if (spotsLeft <= 0) {
            outputArea.setText("Activity is full. Cannot register student.");
            return;
        }

        bookingSystem.getStudentList().add(new Student(studentName, studentGpa, selectedActivity));
        outputArea.setText("Student registered successfully:\n" + studentName + " | GPA: " + studentGpa + " | Activity: " + selectedActivity.getActivityName());
    }

    // Handles unregister student event — shows registered students and removes selected one
    private void handleUnregisterStudent() {

        if (bookingSystem.getStudentList().isEmpty()) {
            outputArea.setText("No registered students.");
            return;
        }

        ArrayList<Student> studentList = bookingSystem.getStudentList();
        String[] studentOptions = new String[studentList.size()];
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            studentOptions[i] = student.getStudentName() + " - " + student.getRegisteredActivity().getActivityName();
        }

        String selectedOption = (String) JOptionPane.showInputDialog(
                this,
                "Select student to unregister:",
                "Unregister Student",
                JOptionPane.PLAIN_MESSAGE,
                null,
                studentOptions,
                studentOptions[0]
        );

        if (selectedOption == null) {
            return;
        }

        // Find and remove the selected student by matching the chosen option string
        for (int i = 0; i < studentOptions.length; i++) {
            if (studentOptions[i].equals(selectedOption)) {
                studentList.remove(i);
                outputArea.setText("Student unregistered successfully.");
                break;
            }
        }
    }

    // Handles display activities event — sorts by date using insertion sort and displays results
    private void handleDisplayActivities() {

        if (bookingSystem.getActivityList().isEmpty()) {
            outputArea.setText("No activities available. Please add an activity first.");
            return;
        }

        // Copy the list so the original insertion order is preserved
        ArrayList<Activity> sortedActivityList = new ArrayList<>(bookingSystem.getActivityList());

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

        StringBuilder output = new StringBuilder("--- Upcoming Activities (Earliest to Latest) ---\n");
        for (Activity activity : sortedActivityList) {
            int spotsLeft = activity.getActivityCapacity() - bookingSystem.countRegistrations(activity.getActivityName());
            output.append(activity.getActivityName()).append(" | ").append(activity.getActivityType()).append(" | ").append(activity.getActivityLocation()).append(" | ").append(activity.getActivityDay()).append("/").append(activity.getActivityMonth()).append("/").append(activity.getActivityYear()).append(" | Capacity: ").append(activity.getActivityCapacity()).append(" | Spots Left: ").append(spotsLeft).append("\n");
        }

        outputArea.setText(output.toString());
    }

    // Handles check capacity event — displays total and remaining spots for each activity
    private void handleCheckCapacity() {

        if (bookingSystem.getActivityList().isEmpty()) {
            outputArea.setText("No activities available. Please add an activity first.");
            return;
        }

        // Shows total capacity alongside remaining spots to give a clear picture of demand
        StringBuilder output = new StringBuilder("--- Activity Capacity ---\n");
        for (Activity activity : bookingSystem.getActivityList()) {
            int spotsLeft = activity.getActivityCapacity() - bookingSystem.countRegistrations(activity.getActivityName());
            output.append(activity.getActivityName()).append(" | Total: ").append(activity.getActivityCapacity()).append(" | Spots Left: ").append(spotsLeft).append("\n");
        }

        outputArea.setText(output.toString());
    }

    // Handles display participants event — shows all registered students grouped by activity
    private void handleDisplayParticipants() {

        if (bookingSystem.getStudentList().isEmpty()) {
            outputArea.setText("No registered students.");
            return;
        }

        // Displays all registered participants across all activities — tracks university engagement
        StringBuilder output = new StringBuilder("--- All Registered Participants ---\n");
        for (Activity activity : bookingSystem.getActivityList()) {
            output.append(activity.getActivityName()).append(" (").append(activity.getActivityType()).append("):\n");
            boolean hasParticipants = false;
            for (Student student : bookingSystem.getStudentList()) {
                if (student.getRegisteredActivity().getActivityName().equals(activity.getActivityName())) {
                    output.append("   - ").append(student.getStudentName()).append(" | GPA: ").append(student.getStudentGpa()).append("\n");
                    hasParticipants = true;
                }
            }
            if (!hasParticipants) {
                output.append("   No participants registered.\n");
            }
        }

        outputArea.setText(output.toString());
    }
}