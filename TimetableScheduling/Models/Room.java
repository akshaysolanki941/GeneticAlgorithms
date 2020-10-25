package TimetableScheduling.Models;

/**
 * This class is representing a room or lecture hall.
 * 
 * @author Akshay Solanki
 */

public class Room {

    private int roomId;
    private String roomNo;
    private int capacity;

    public Room(int roomId, String roomNo, int capacity) {
        this.roomId = roomId;
        this.roomNo = roomNo;
        this.capacity = capacity;
    }

    public int getRoomId() {
        return this.roomId;
    }

    public String getRoomNumber() {
        return this.roomNo;
    }

    public int getRoomCapacity() {
        return this.capacity;
    }
}