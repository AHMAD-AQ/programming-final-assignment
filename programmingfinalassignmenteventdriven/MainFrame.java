package programmingfinalassignmenteventdriven;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Event-driven paradigm entry point — creates the main window and shows the role selection screen
public class MainFrame extends JFrame {

    // BookingSystem object shared across all panels — owns all data and logic
    private BookingSystem bookingSystem;

    // Constructor — sets up the main window
    public MainFrame() {
        bookingSystem = new BookingSystem();

        // Full screen setup
        setTitle("Smart Campus Activity Booking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        showRolePanel();
        setVisible(true);
    }

    // Builds and displays the role selection screen
    public void showRolePanel() {
        getContentPane().removeAll();
        getContentPane().setLayout(null);

        // Title label at the top center
        JLabel titleLabel = new JLabel("=== Smart Campus Activity Booking System ===", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(300, 180, 800, 50);
        getContentPane().add(titleLabel);

        // Prompt label
        JLabel promptLabel = new JLabel("Select your role:", SwingConstants.CENTER);
        promptLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        promptLabel.setBounds(500, 250, 400, 40);
        getContentPane().add(promptLabel);

        // Organizer button — fires an event that loads the organizer panel
        JButton organizerButton = new JButton("Organizer");
        organizerButton.setFont(new Font("Arial", Font.PLAIN, 18));
        organizerButton.setBounds(550, 310, 300, 50);
        getContentPane().add(organizerButton);

        // Student button — fires an event that loads the student panel
        JButton studentButton = new JButton("Student");
        studentButton.setFont(new Font("Arial", Font.PLAIN, 18));
        studentButton.setBounds(550, 380, 300, 50);
        getContentPane().add(studentButton);

        // University button — fires an event that loads the university panel
        JButton universityButton = new JButton("University");
        universityButton.setFont(new Font("Arial", Font.PLAIN, 18));
        universityButton.setBounds(550, 450, 300, 50);
        getContentPane().add(universityButton);

        // Quit button — fires an event that exits the program
        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        quitButton.setBounds(550, 520, 300, 50);
        getContentPane().add(quitButton);

        // ActionListeners — respond to button click events
        organizerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBookingPanel(1);
            }
        });

        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBookingPanel(2);
            }
        });

        universityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBookingPanel(3);
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        revalidate();
        repaint();
    }

    // Builds and displays the booking panel for the selected role
    public void showBookingPanel(int role) {
        getContentPane().removeAll();

        BookingPanel bookingPanel = new BookingPanel(bookingSystem, role, this);
        bookingPanel.setBounds(0, 0, getWidth(), getHeight());
        getContentPane().add(bookingPanel);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        // Ensures the GUI is created on the Event Dispatch Thread — standard Swing practice
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}