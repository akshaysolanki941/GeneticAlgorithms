package TimetableScheduling.Models;

/**
 * This class is representing a timeslot.
 * 
 * @author Akshay Solanki
 */

public class Timeslot {

    private int timeslotId;
    private String timeslot;

    public Timeslot(int timeslotId, String timeslot) {
        this.timeslotId = timeslotId;
        this.timeslot = timeslot;
    }

    public int getTimeslotId() {
        return this.timeslotId;
    }

    public String getTimeslot() {
        return this.timeslot;
    }
}