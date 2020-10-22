package TravellingSalesmanProblem;

/*
    @Author : Akshay Solanki
    This class is representing an individual - chromosome and fitness score.
*/

public class Individual {

    private int[] chromosome;
    private double fitness = -1;
    private double distance = -1;

    // contructor
    public Individual(int[] chromosome) {
        this.chromosome = chromosome;
    }

    /*
     * This contructor is generating a chromosome. Here we are not shuffling the
     * genes as mutation and crossover will take care of it.
     */
    public Individual(int chromosomeLength) {
        int[] arr = new int[chromosomeLength];

        for (int gene = 0; gene < chromosomeLength; gene++) {
            arr[gene] = gene;
        }

        this.chromosome = arr;
    }

    public int[] getChromosome() {
        return this.chromosome;
    }

    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    public int getGene(int index) {
        return this.chromosome[index];
    }

    public void setGene(int index, int gene) {
        this.chromosome[index] = gene;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return this.fitness;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return this.distance;
    }

    /*
     * It is checking wheather a particular gene is already present in the
     * chromosome or not.
     */
    public boolean containsGene(int gene) {
        for (int i = 0; i < this.chromosome.length; i++) {
            if (this.chromosome[i] == gene) {
                return true;
            }
        }
        return false;
    }
}