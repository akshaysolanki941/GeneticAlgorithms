package TravellingSalesmanProblem;

import java.util.*;

/*
    @Author : Akshay Solanki
    This is the main driver class.
*/

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int maxGenerations = sc.nextInt();
        int numCities = sc.nextInt();
        int populationSize = sc.nextInt();
        double mutationRate = sc.nextDouble();
        double crossoverRate = sc.nextDouble();
        int poolSize = sc.nextInt();
        int eliteThreshold = sc.nextInt();

        TSP tsp = new TSP(maxGenerations, numCities, populationSize, mutationRate, crossoverRate, eliteThreshold, poolSize);

        tsp.solve();

        sc.close();
    }
}
