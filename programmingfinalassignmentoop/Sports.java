package programmingfinalassignmentoop;

// Inherits all fields and methods from Activity — only adds its specific type label
public class Sports extends Activity {

    // Constructor — passes all arguments up to the parent Activity constructor
    public Sports(String activityName, String activityLocation, int activityCapacity, String activityDay, String activityMonth, String activityYear) {
        super(activityName, activityLocation, activityCapacity, activityDay, activityMonth, activityYear);
    }

    // Provides the type label specific to this subclass
    @Override
    public String getActivityType() {
        return "Sports";
    }
}