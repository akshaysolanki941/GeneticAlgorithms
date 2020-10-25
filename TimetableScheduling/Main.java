package TimetableScheduling;

import java.util.*;

/**
 * This is the main driver class.
 * 
 * @author Akshay Solanki
 */

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Timetable timetable = initializeTimetable();
        int maxGenerations = sc.nextInt();
        int populationSize = sc.nextInt();
        double mutationRate = sc.nextDouble();
        double crossoverRate = sc.nextDouble();
        int eliteCount = sc.nextInt();
        int poolSize = sc.nextInt();

        Schedule schedule = new Schedule(timetable, maxGenerations, populationSize, mutationRate, crossoverRate, eliteCount, poolSize);

        schedule.solve();
        sc.close();
    }

    private static Timetable initializeTimetable() {

        Timetable timetable = new Timetable();

        timetable.addRoom(1, "A1", 15);
        timetable.addRoom(2, "B1", 30);
        timetable.addRoom(3, "D1", 20);
        timetable.addRoom(4, "F1", 25);

        timetable.addTimeslot(1, "Mon 9:00 - 11:00");
        timetable.addTimeslot(2, "Mon 11:00 - 13:00");
        timetable.addTimeslot(3, "Mon 13:00 - 15:00");
        timetable.addTimeslot(4, "Tue 9:00 - 11:00");
        timetable.addTimeslot(5, "Tue 11:00 - 13:00");
        timetable.addTimeslot(6, "Tue 13:00 - 15:00");
        timetable.addTimeslot(7, "Wed 9:00 - 11:00");
        timetable.addTimeslot(8, "Wed 11:00 - 13:00");
        timetable.addTimeslot(9, "Wed 13:00 - 15:00");
        timetable.addTimeslot(10, "Thu 9:00 - 11:00");
        timetable.addTimeslot(11, "Thu 11:00 - 13:00");
        timetable.addTimeslot(12, "Thu 13:00 - 15:00");
        timetable.addTimeslot(13, "Fri 9:00 - 11:00");
        timetable.addTimeslot(14, "Fri 11:00 - 13:00");
        timetable.addTimeslot(15, "Fri 13:00 - 15:00");

        timetable.addFaculty(1, "Dr P Smith");
        timetable.addFaculty(2, "Mrs E Mitchell");
        timetable.addFaculty(3, "Dr R Williams");
        timetable.addFaculty(4, "Mr A Thompson");

        timetable.addCourse(1, "IDS", "Introduction to Data Science", new int[] { 1, 2 });
        timetable.addCourse(2, "CS", "Computer Security", new int[] { 1, 3 });
        timetable.addCourse(3, "AI", "Artificial Intelligence", new int[] { 1, 2 });
        timetable.addCourse(4, "OS", "Operating Systems", new int[] { 3, 4 });
        timetable.addCourse(5, "DBMS", "Database Management", new int[] { 4 });
        timetable.addCourse(6, "CN", "Computer Networks", new int[] { 1, 4 });

        timetable.addBatch(1, 10, new int[] { 1, 3, 4 });
        timetable.addBatch(2, 30, new int[] { 2, 3, 5, 6 });
        timetable.addBatch(3, 18, new int[] { 3, 4, 5 });
        timetable.addBatch(4, 25, new int[] { 1, 4 });
        timetable.addBatch(5, 20, new int[] { 2, 3, 5 });
        timetable.addBatch(6, 22, new int[] { 1, 4, 5 });
        timetable.addBatch(7, 16, new int[] { 1, 3 });
        timetable.addBatch(8, 18, new int[] { 2, 6 });
        timetable.addBatch(9, 24, new int[] { 1, 6 });
        timetable.addBatch(10, 25, new int[] { 3, 4 });

        return timetable;
    }
}
