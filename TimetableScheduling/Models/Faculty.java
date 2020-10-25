package TimetableScheduling.Models;

/**
 * This class is representing a faculty or professor.
 * 
 * @author Akshay Solanki
 */

public class Faculty {
    private int facultyId;
    private String facultyName;

    public Faculty(int facultyId, String facultyName) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
    }

    public int getFacultyId() {
        return this.facultyId;
    }

    public String getFacultyName() {
        return this.facultyName;
    }
}