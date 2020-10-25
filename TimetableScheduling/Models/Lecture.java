package TimetableScheduling.Models;

/**
 * This class is representing a lecture in which a room is alloted to a batch
 * and faculty to teach a particular course in a particular timeslot.
 * 
 * @author Akshay Solanki
 */

public class Lecture {
    private int lectureId;
    private int batchId;
    private int courseId;
    private int facultyId;
    private int roomId;
    private int timeslotId;

    public Lecture(int lectureId, int batchId, int courseId, int facultyId, int roomId, int timeslotId) {
        this.lectureId = lectureId;
        this.batchId = batchId;
        this.courseId = courseId;
        this.facultyId = facultyId;
        this.roomId = roomId;
        this.timeslotId = timeslotId;
    }

    public int getLectureId() {
        return this.lectureId;
    }

    public int getBatchId() {
        return this.batchId;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public int getFacultyId() {
        return this.facultyId;
    }

    public int getRoomId() {
        return this.roomId;
    }

    public int getTimeslotId() {
        return this.timeslotId;
    }

}