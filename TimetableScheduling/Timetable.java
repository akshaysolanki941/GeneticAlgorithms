package TimetableScheduling;

import java.util.HashMap;
import java.util.Random;

import TimetableScheduling.Models.*;

/**
 * This class is used to store the information of faculties, courses, batches,
 * timeslots and rooms. It is also creating lectures from the individual's
 * chromosome so that the timetable can be printed in a human readable form. It
 * is also calculating number of clashes in the lectures schedule made from a
 * chromosome.
 * 
 * @author Akshay Solanki
 */

public class Timetable {
    private Lecture[] lectures;
    private int noOfLectures;
    private HashMap<Integer, Course> courses;
    private HashMap<Integer, Faculty> faculties;
    private HashMap<Integer, Batch> batches;
    private HashMap<Integer, Room> rooms;
    private HashMap<Integer, Timeslot> timeslots;

    public Timetable() {
        this.courses = new HashMap<>();
        this.faculties = new HashMap<>();
        this.batches = new HashMap<>();
        this.rooms = new HashMap<>();
        this.timeslots = new HashMap<>();
    }

    public void addRoom(int roomId, String roomNo, int capacity) {
        rooms.put(roomId, new Room(roomId, roomNo, capacity));
    }

    public void addCourse(int courseId, String courseCode, String courseName, int[] facultyIds) {
        courses.put(courseId, new Course(courseId, courseCode, courseName, facultyIds));
    }

    public void addFaculty(int facultyId, String facultyName) {
        faculties.put(facultyId, new Faculty(facultyId, facultyName));
    }

    public void addBatch(int batchId, int batchSize, int[] courseIds) {
        batches.put(batchId, new Batch(batchId, batchSize, courseIds));
    }

    public void addTimeslot(int timeslotId, String timeslot) {
        timeslots.put(timeslotId, new Timeslot(timeslotId, timeslot));
    }

    public void createLectures(Individual individual) {
        Lecture[] lectures = new Lecture[this.noOfLectures];

        int chromosome[] = individual.getChromosome();
        int lectureId = 0;
        int gene = 0;

        for (int batchId : this.batches.keySet()) {
            int courseIds[] = this.batches.get(batchId).getCourseIds();

            for (int courseId : courseIds) {
                int timeslotId = chromosome[gene];
                gene++;

                int roomId = chromosome[gene];
                gene++;

                int facultyId = chromosome[gene];
                gene++;

                lectures[lectureId] = new Lecture(lectureId, batchId, courseId, facultyId, roomId, timeslotId);
                lectureId++;
            }
        }

        this.lectures = lectures;
    }

    public Room getRoom(int roomId) {
        return this.rooms.get(roomId);
    }

    public Course getCourse(int courseId) {
        return this.courses.get(courseId);
    }

    public Faculty getFaculty(int facultyId) {
        return this.faculties.get(facultyId);
    }

    public Timeslot getTimeslot(int timeslotId) {
        return this.timeslots.get(timeslotId);
    }

    public Batch getBatch(int batchId) {
        return this.batches.get(batchId);
    }

    public Batch[] getBatches() {
        return (Batch[]) this.batches.values().toArray(new Batch[this.batches.size()]);
    }

    public Lecture[] getLectures() {
        return this.lectures;
    }

    public Room getRandomRoom() {
        Object[] arr = this.rooms.values().toArray();
        return (Room) arr[new Random().nextInt(arr.length)];
    }

    public Timeslot getRandomTimeslot() {
        Object[] arr = this.timeslots.values().toArray();
        return (Timeslot) arr[new Random().nextInt(arr.length)];
    }

    public int getNoOfLectures() {
        if (this.noOfLectures > 0) {
            return this.noOfLectures;
        }

        int noOfLectures = 0;
        for (int batchId : this.batches.keySet()) {
            noOfLectures += this.batches.get(batchId).getCourseIds().length;
        }

        this.noOfLectures = noOfLectures;
        return this.noOfLectures;
    }

    public int getClashes() {
        int clashes = 0;

        for (Lecture lecture1 : this.lectures) {
            int roomCapacity = this.getRoom(lecture1.getRoomId()).getRoomCapacity();
            int batchSize = this.getBatch(lecture1.getBatchId()).getBatchSize();

            if (roomCapacity < batchSize) {
                clashes++;
            }

            for (Lecture lecture2 : lectures) {
                boolean flag1 = false;
                boolean flag2 = false;

                if (lecture1.getLectureId() != lecture2.getLectureId()) {
                    if (lecture1.getTimeslotId() == lecture2.getTimeslotId()) {
                        if (lecture1.getFacultyId() == lecture2.getFacultyId() && !flag1) {
                            clashes++;
                            flag1 = true;
                        }

                        if (lecture1.getRoomId() == lecture2.getRoomId() && !flag2) {
                            clashes++;
                            flag2 = true;
                        }

                        if (flag1 && flag2) {
                            break;
                        }
                    }
                }
            }
        }

        return clashes;
    }
}