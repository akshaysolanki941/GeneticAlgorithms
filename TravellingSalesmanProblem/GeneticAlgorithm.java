package TravellingSalesmanProblem;

import java.util.*;

/*
    @Author : Akshay Solanki
    This class contains all the methods or we can say the operators of the genetic algorithm.
*/

public class GeneticAlgorithm {
    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int poolSize;
    private int eliteThreshold;

    // constructor
    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int eliteThreshold, int poolSize) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.eliteThreshold = eliteThreshold;
        this.poolSize = poolSize;
    }

    public Population initPopulation(int chromosomeLength) {
        return new Population(this.populationSize, chromosomeLength);
    }

    // when to terminate the evolution of generations
    public boolean terminate(int currGenerations, int maxGenerations) {
        return (currGenerations > maxGenerations);
    }

    // this method is assigning a fitness score to each individual in the population
    public void assignFitness(Population population, City cities[]) {
        for (Individual individual : population.getPopulation()) {
            double distance = getDistance(individual, cities);
            double fitness = calculateFitness(distance);
            individual.setFitness(fitness);
            individual.setDistance(distance);
        }
    }

    /*
     * this method is randomly selecting some poolSize number of individuals and
     * returning the best individual among that pool
     */
    public Individual selectParent(Population population) {
        Population pool = new Population(this.poolSize);

        for (int i = 0; i < this.poolSize; i++) {
            Individual individual = population.getIndividual(new Random().nextInt(population.getPopulationSize()));
            pool.setIndividual(i, individual);
        }

        return pool.getFittestIndividual(0);
    }

    /*
     * This method is selecting two parents from the population and producing the
     * offspring from those parents. A random index 'idx' is generated. Then the
     * genes of chromosome of parent1 starting from 0 to idx is copied to the
     * offSpring chromosome. Now the remaining genes of offSpring chromosome is
     * copied from the genes of chromosome of parent2 if that gene is not already
     * present in it because the cities can't be repeated.
     */
    public Population crossover(Population population) {
        Population newPopulation = new Population(population.getPopulationSize());

        for (int i = 0; i < population.getPopulationSize(); i++) {
            Individual parent1 = population.getFittestIndividual(i);

            /* deciding wheather to do crossover or not */
            if (this.crossoverRate > Math.random()) {
                Individual parent2 = selectParent(population);

                int chromosome[] = new int[parent1.getChromosomeLength()];
                Arrays.fill(chromosome, -1);
                Individual offSpring = new Individual(chromosome);

                int idx = (int) Math.random() % parent1.getChromosomeLength();
                for (int j = 0; j <= idx; j++) {
                    offSpring.setGene(j, parent1.getGene(j));
                }

                int k = idx + 1;
                for (int j = 0; j < parent2.getChromosomeLength(); j++) {
                    if (!offSpring.containsGene(parent2.getGene(j))) {
                        offSpring.setGene(k++, parent2.getGene(j));
                    }
                }

                newPopulation.setIndividual(i, offSpring);

            } else {
                newPopulation.setIndividual(i, parent1);
            }
        }

        return newPopulation;
    }

    /*
     * After doing crossover, this method is mutating the individuals of the new
     * generation. Here mutating is done by just swapping two genes by generating
     * random indexes.
     */
    public Population mutate(Population population) {
        Population newPopulation = new Population(population.getPopulationSize());

        for (int i = 0; i < population.getPopulationSize(); i++) {
            Individual individual = population.getFittestIndividual(i);

            /* deciding wheather to mutate this individual or chromosome or not */
            if (i >= eliteThreshold) {

                for (int j = 0; j < individual.getChromosomeLength(); j++) {

                    /* deciding wheather to mutate this gene or not */
                    if (this.mutationRate > Math.random()) {
                        int idx = new Random().nextInt(individual.getChromosomeLength());

                        int gene1 = individual.getGene(j);
                        int gene2 = individual.getGene(idx);

                        individual.setGene(j, gene2);
                        individual.setGene(idx, gene1);
                    }
                }
            }

            newPopulation.setIndividual(i, individual);
        }

        return newPopulation;
    }

    /*
     * This is calculating the fitness score of a particular individual or
     * chromosome. In the context of TSP, the more is the distance of the route, the
     * less is the fitness score of that route.
     */
    private double calculateFitness(double distance) {
        double fitness = 1 / distance;
        return fitness;
    }

    /*
     * This is calculating the total distance of the route represented by an
     * individual or chromosome. Euclidean distance is used here.
     */
    private double getDistance(Individual individual, City cities[]) {
        double distance = 0;
        int chromosome[] = individual.getChromosome();

        for (int i = 0, j = i + 1; i < chromosome.length; i++) {
            City cityA = cities[chromosome[i]];
            City cityB = cities[chromosome[j % chromosome.length]];

            double p = Math.pow((cityA.getX() - cityB.getX()), 2);
            double q = Math.pow((cityA.getY() - cityB.getY()), 2);

            distance += Math.sqrt(p + q);
        }

        return distance;
    }
}
