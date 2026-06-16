package programmingfinalassignmenteventdriven;

// Inherits all fields and methods from Activity — only adds its specific type label
public class Seminar extends Activity {

    // Constructor — passes all arguments up to the parent Activity constructor
    public Seminar(String activityName, String activityLocation, int activityCapacity, String activityDay, String activityMonth, String activityYear) {
        super(activityName, activityLocation, activityCapacity, activityDay, activityMonth, activityYear);
    }

    // Provides the type label specific to this subclass
    @Override
    public String getActivityType() {
        return "Seminar";
    }
}
