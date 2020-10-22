package TravellingSalesmanProblem;

/*
    @Author : Akshay Solanki
    A city is represented by its x and y coordinates
*/

public class City {
    private int x, y;

    // contructor
    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}