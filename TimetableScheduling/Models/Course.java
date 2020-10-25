package TimetableScheduling.Models;

import java.util.Random;

/**
 * This class is representing a course or subject.
 * 
 * @author Akshay Solanki
 */

public class Course {
    private int courseId;
    private String courseCode;
    private String courseName;
    private int[] facultyIds;

    public Course(int courseId, String courseCode, String courseName, int[] facultyIds) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.facultyIds = facultyIds;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public int[] getFacultyIds() {
        return this.facultyIds;
    }

    public int getRandomFacultyId() {
        return facultyIds[new Random().nextInt(facultyIds.length)];
    }
}