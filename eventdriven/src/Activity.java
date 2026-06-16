package programmingfinalassignmenteventdriven;

// Parent class — holds all shared fields that every activity type has in common
// Inheritance: Seminar, Sports, and Workshop all extend this class
public abstract class Activity {

    // Encapsulated fields — private so they can only be accessed through getters and setters
    private String activityName;
    private String activityLocation;
    private int activityCapacity;
    private String activityDay;
    private String activityMonth;
    private String activityYear;
    // dateKey format YYYYMMDD allows correct chronological string comparison in insertion sort
    private String dateKey;

    // Constructor — called by subclass constructors via super()
    public Activity(String activityName, String activityLocation, int activityCapacity, String activityDay, String activityMonth, String activityYear) {
        this.activityName = activityName;
        this.activityLocation = activityLocation;
        this.activityCapacity = activityCapacity;
        this.activityDay = activityDay;
        this.activityMonth = activityMonth;
        this.activityYear = activityYear;
        this.dateKey = activityYear + activityMonth + activityDay;
    }

    // Abstract method — every subclass must provide its own activity type label
    public abstract String getActivityType();

    // Getters — provide controlled read access to private fields
    public String getActivityName() {
        return activityName;
    }

    public String getActivityLocation() {
        return activityLocation;
    }

    public int getActivityCapacity() {
        return activityCapacity;
    }

    public String getActivityDay() {
        return activityDay;
    }

    public String getActivityMonth() {
        return activityMonth;
    }

    public String getActivityYear() {
        return activityYear;
    }

    public String getDateKey() {
        return dateKey;
    }
}
