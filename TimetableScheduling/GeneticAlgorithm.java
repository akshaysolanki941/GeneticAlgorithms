package TimetableScheduling;

import java.util.*;

/**
 * This class contains various operators of genetic algorithm like mutate and
 * crossover.
 * 
 * @author Akshay Solanki
 */

public class GeneticAlgorithm {

    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int eliteCount;
    private int poolSize;

    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int eliteCount, int poolSize) {

        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.eliteCount = eliteCount;
        this.poolSize = poolSize;
    }

    public Population initPopulation(Timetable timetable) {
        return new Population(this.populationSize, timetable);
    }

    public boolean terminate(int currGeneration, int maxGenerations, Population population) {
        return ((currGeneration > maxGenerations) || (population.getFittestIndividual(0).getFitness() == 1.0));
    }

    public void assignFitness(Population population, Timetable timetable) {
        for (Individual individual : population.getPopulation()) {
            int clashes = calculateClashes(individual, timetable);
            double fitness = calculateFitness(clashes);
            individual.setFitness(fitness);
            individual.setClashes(clashes);
        }
    }

    private Individual selectParent(Population population) {
        Population pool = new Population(this.poolSize);

        for (int i = 0; i < this.poolSize; i++) {
            Individual individual = population.getIndividual(new Random().nextInt(population.getPopulationSize()));
            pool.setIndividual(i, individual);
        }

        return pool.getFittestIndividual(0);
    }

    private double calculateFitness(int clashes) {
        double fitness = 1 / (double) (clashes + 1);
        return fitness;
    }

    private int calculateClashes(Individual individual, Timetable timetable) {
        timetable.createLectures(individual);
        return timetable.getClashes();
    }

    /**
     * It is generating a random individual. And some genes of our individual is
     * swapped with the corresponding genes of the random individual.
     */
    public Population mutate(Population population, Timetable timetable) {
        Population newPopulation = new Population(this.populationSize);

        for (int i = 0; i < this.populationSize; i++) {
            Individual individual = population.getFittestIndividual(i);
            Individual randomIndividual = new Individual(timetable);

            if (i > this.eliteCount) {
                for (int j = 0; j < individual.getChromosomeLength(); j++) {
                    if (this.mutationRate > Math.random()) {
                        individual.setGene(j, randomIndividual.getGene(j));
                    }
                }
            }

            newPopulation.setIndividual(i, individual);
        }

        return newPopulation;
    }

    /**
     * Offspring gets half genes from parent1 and half genes from parent2.
     */
    public Population crossover(Population population) {
        Population newPopulation = new Population(this.populationSize);

        for (int i = 0; i < this.populationSize; i++) {
            Individual parent1 = population.getFittestIndividual(i);

            if (this.crossoverRate > Math.random()) {
                Individual parent2 = selectParent(population);
                Individual offspring = new Individual(parent1.getChromosomeLength());

                for (int j = 0; j < parent1.getChromosomeLength(); j++) {

                    if (0.5 > Math.random()) {
                        offspring.setGene(j, parent1.getGene(j));
                    } else {
                        offspring.setGene(j, parent2.getGene(j));
                    }

                }

                newPopulation.setIndividual(i, offspring);
            } else {
                newPopulation.setIndividual(i, parent1);
            }
        }

        return newPopulation;
    }
}
