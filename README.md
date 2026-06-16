Smart Campus Activity Booking System ☕🎓

A Java-based campus activity booking system built to help students browse, register for, and unregister from university activities without the usual “who has the spreadsheet?” chaos.

This project started as a programming final assignment, but I’m using it as part of my GitHub journey to document my progress, improve my code structure, and build a cleaner developer portfolio.

What It Does

The system allows different university users to interact with campus activities based on their role:

* Students can view upcoming activities, register for activities, and unregister if needed.
* Organisers can manage activity details such as name, type, location, capacity, and date.
* University staff can view registered students and monitor participation.

It also prevents overbooking, because letting 90 students register for a 30-seat workshop is not “scalability” — it is a bug.

Main Features

* View upcoming campus activities
* Sort activities chronologically by date
* Register students for activities
* Unregister students from activities
* Manage activity details
* Track registered students
* Prevent activity overbooking
* GUI-based interaction using Java Swing

Tech Stack

* Language: Java
* IDE Used: Eclipse
* GUI: Java Swing
* Core Concepts:
    * Procedural programming
    * Object-oriented programming
    * Event-driven programming
    * Insertion sort algorithm
    * Debugging and input validation

Project Structure

The project was implemented using three programming approaches:

1. Procedural Version

The first version uses a procedural structure, with program data stored mainly through arrays and static methods. This helped establish the basic logic of the system, but it also showed the limitations of procedural design when the program started growing.

2. Object-Oriented Version

The second version improves the structure by introducing classes such as:

* Activity
* Seminar
* Sports
* Workshop
* Student
* BookingSystem
* Main

This made the code cleaner, easier to read, and more maintainable.

3. Event-Driven Version

The final version builds on the object-oriented system and adds a graphical user interface using Java Swing.

Additional GUI classes include:

* MainFrame
* BookingPanel

This version allows users to interact with buttons, dialogs, and panels instead of relying only on console input.

Algorithm Used

The system uses insertion sort to display activities in chronological order.

Each activity has a dateKey, such as:

20260601

This format makes it easier to compare dates and sort activities correctly.

Insertion sort was chosen because the system handles a relatively small list of activities, making it simple and suitable for the project.

What I Learned

This project helped me practice:

* Building a Java application from planning to implementation
* Comparing procedural, object-oriented, and event-driven programming
* Designing classes and inheritance relationships
* Using Java Swing for GUI development
* Debugging logic errors in Eclipse
* Handling user input safely
* Preventing overbooking through validation
* Writing cleaner and more maintainable code

Known Limitations

This project is still a learning project, not a production-ready booking platform.

Current limitations include:

* No database integration
* Data is not permanently saved after closing the program
* GUI design is functional but simple
* No login/authentication system
* Limited input validation compared to a real-world system

Future Improvements

Possible upgrades:

* Add database storage using MySQL or SQLite
* Add user login for students, organisers, and admins
* Improve the GUI design
* Add search and filter options
* Export registration lists
* Add unit tests
* Refactor GUI logic into smaller classes

Why This Project Matters

This project represents one of my first steps toward building a public programming portfolio. It is not perfect, but it shows my progress in Java, software design, debugging, and turning a basic idea into a working application.

Small project. Real learning. No fake guru energy.
