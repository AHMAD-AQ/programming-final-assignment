package programmingfinalassignmenteventdriven;

// Composition — Student holds a reference to an Activity object rather than storing activity data as raw strings
public class Student {

    private String studentName;
    private String studentGpa;
    // Composition: Student is composed of an Activity — it owns a reference to one
    private Activity registeredActivity;

    // Constructor — takes student details and the activity they are registering for
    public Student(String studentName, String studentGpa, Activity registeredActivity) {
        this.studentName = studentName;
        this.studentGpa = studentGpa;
        this.registeredActivity = registeredActivity;
    }

    // Getters — provide controlled read access to private fields
    public String getStudentName() {
        return studentName;
    }

    public String getStudentGpa() {
        return studentGpa;
    }

    public Activity getRegisteredActivity() {
        return registeredActivity;
    }
}