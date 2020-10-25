package TimetableScheduling;

import java.util.*;

/**
 * This class is representing a population of individuals.
 * 
 * @author Akshay Solanki
 */

public class Population {
    private Individual[] population;

    public Population(int populationSize) {
        this.population = new Individual[populationSize];
    }

    public Population(int populationSize, Timetable timetable) {

        Individual population[] = new Individual[populationSize];

        for (int i = 0; i < populationSize; i++) {
            population[i] = new Individual(timetable);
        }

        this.population = population;
    }

    public Population(int populationSize, int chromosomeLength) {

        Individual population[] = new Individual[populationSize];

        for (int i = 0; i < populationSize; i++) {
            population[i] = new Individual(chromosomeLength);
        }

        this.population = population;
    }

    public Individual getFittestIndividual(int index) {

        Arrays.sort(this.population, new MyComp());

        return this.population[index];
    }

    public Individual[] getPopulation() {
        return this.population;
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

/**
 * A custom comparator to sort the population on the basis of individual's
 * fitness scores.
 */
class MyComp implements Comparator<Individual> {
    public int compare(Individual i1, Individual i2) {
        return Double.compare(i2.getFitness(), i1.getFitness());
    }
}