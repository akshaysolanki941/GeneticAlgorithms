package TimetableScheduling.Models;

/**
 * This class is representing a batch of students.
 * 
 * @author Akshay Solanki
 */

public class Batch {
    private int batchId;
    private int batchSize;
    private int[] courseIds;

    public Batch(int batchId, int batchSize, int[] courseIds) {
        this.batchId = batchId;
        this.batchSize = batchSize;
        this.courseIds = courseIds;
    }

    public int getBatchId() {
        return this.batchId;
    }

    public int getBatchSize() {
        return this.batchSize;
    }

    public int[] getCourseIds() {
        return this.courseIds;
    }
}