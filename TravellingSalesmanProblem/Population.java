package TravellingSalesmanProblem;

import java.util.*;

/*
    @Author : Akshay Solanki
    This class is representing the population of individuals.
*/

public class Population {

    private Individual[] population;

    // contructor
    public Population(int populationSize) {
        this.population = new Individual[populationSize];
    }

    // This contructor is initializing the first population.
    public Population(int populationSize, int chromosomeLength) {
        this.population = new Individual[populationSize];

        for (int i = 0; i < populationSize; i++) {
            this.population[i] = new Individual(chromosomeLength);
        }
    }

    public Individual[] getPopulation() {
        return this.population;
    }

    /*
     * This is returning the fittest individual, i.e. individual having highest
     * fitness score, among the population. The population is sorted in a decreasing
     * order of the fitness scores.
     */
    public Individual getFittestIndividual(int index) {
        Arrays.sort(this.population, new MyComp());

        return this.population[index];
    }

    public int getPopulationSize() {
        return this.population.length;
    }

    public Individual getIndividual(int index) {
        return this.population[index];
    }

    public void setIndividual(int index, Individual individual) {
        this.population[index] = individual;
    }
}

/*
 * Custom comparator to sort the population on basis of fitness scores of
 * individuals.
 */
class MyComp implements Comparator<Individual> {
    public int compare(Individual i1, Individual i2) {
        return Double.compare(i2.getFitness(), i1.getFitness());
    }
}