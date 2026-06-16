package programmingfinalassignmentoop;

// Inherits all fields and methods from Activity — only adds its specific type label
public class Workshop extends Activity {

    // Constructor — passes all arguments up to the parent Activity constructor
    public Workshop(String activityName, String activityLocation, int activityCapacity, String activityDay, String activityMonth, String activityYear) {
        super(activityName, activityLocation, activityCapacity, activityDay, activityMonth, activityYear);
    }

    // Provides the type label specific to this subclass
    @Override
    public String getActivityType() {
        return "Workshop";
    }
}