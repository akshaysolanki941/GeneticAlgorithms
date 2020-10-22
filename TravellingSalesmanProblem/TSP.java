package TravellingSalesmanProblem;

import java.util.*;

/*
    @Author : Akshay Solanki
    This class is simulating the evolution process in the context of TSP.
*/

public class TSP {

    private int maxGenerations;
    private int numCities;
    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int poolSize;
    private int eliteThreshold;

    // contructor
    public TSP(int maxGenerations, int numCities, int populationSize, double mutationRate, double crossoverRate, int eliteThreshold, int poolSize) {
        this.maxGenerations = maxGenerations;
        this.numCities = numCities;
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.eliteThreshold = eliteThreshold;
        this.poolSize = poolSize;
    }

    /*
     * This method is generating a map by generating random cities. After creating
     * an initial population from the map of cities, it is printing the best
     * individual after each evolution, i.e., the best individual of a particular
     * generation.
     */
    public void solve() {

        City cities[] = new City[numCities];
        int currGeneration = 1;
        Individual bestAmongAllGenerations = new Individual(new int[numCities]);
        bestAmongAllGenerations.setFitness(Double.MIN_VALUE);

        for (int i = 0; i < numCities; i++) {
            int x = new Random().nextInt(100);
            int y = new Random().nextInt(100);

            cities[i] = new City(x, y);
        }

        GeneticAlgorithm ga = new GeneticAlgorithm(populationSize, mutationRate, crossoverRate, eliteThreshold, poolSize);

        Population population = ga.initPopulation(numCities);
        ga.assignFitness(population, cities);

        while (!ga.terminate(currGeneration, maxGenerations)) {

            Individual fittestIndividual = population.getFittestIndividual(0);
            System.out.println("Best of generation number " + (currGeneration - 1) + " :");
            printDetails(fittestIndividual);

            if (fittestIndividual.getFitness() > bestAmongAllGenerations.getFitness()) {
                bestAmongAllGenerations = fittestIndividual;
            }

            population = ga.crossover(population);
            population = ga.mutate(population);
            ga.assignFitness(population, cities);

            currGeneration++;
        }

        Individual fittestIndividual = population.getFittestIndividual(0);
        System.out.println("Best of generation number " + (currGeneration - 1) + " :");
        printDetails(fittestIndividual);

        if (fittestIndividual.getFitness() > bestAmongAllGenerations.getFitness()) {
            bestAmongAllGenerations = fittestIndividual;
        }

        System.out.println("\nThe best individual among all " + maxGenerations + " generations is :");
        printDetails(bestAmongAllGenerations);
    }

    /*
     * This is printing the details of the individual like the route it is
     * representing, the distance of the route and the fitness score of the
     * individual.
     */
    private void printDetails(Individual individual) {
        int[] route = individual.getChromosome();
        System.out.println("Route : " + Arrays.toString(route));
        System.out.println("Distance : " + individual.getDistance());
        System.out.println("Fitness : " + individual.getFitness());
        System.out.println();
    }
}
