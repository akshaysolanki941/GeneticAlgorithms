package TimetableScheduling;

import java.util.*;

import TimetableScheduling.Models.Lecture;

/**
 * This class is finding a schedule with minimum number of clashes using
 * genetic algorithm.
 * 
 * @author Akshay Solanki
 */

public class Schedule {

    private Timetable timetable;
    private int maxGenerations;
    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int eliteCount;
    private int poolSize;

    public Schedule(Timetable timetable, int maxGenerations, int populationSize, double mutationRate,
            double crossoverRate, int eliteCount, int poolSize) {
        this.timetable = timetable;
        this.maxGenerations = maxGenerations;
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.eliteCount = eliteCount;
        this.poolSize = poolSize;
    }

    public void solve() {

        GeneticAlgorithm ga = new GeneticAlgorithm(populationSize, mutationRate, crossoverRate, eliteCount, poolSize);
        Population population = ga.initPopulation(timetable);
        ga.assignFitness(population, timetable);
        int currGeneration = 1;

        Individual bestAmongAllGenerations = population.getFittestIndividual(0);

        while (!ga.terminate(currGeneration, maxGenerations, population)) {

            Individual individual = population.getFittestIndividual(0);
            System.out.println("Best of generation number " + (currGeneration - 1) + " :");
            printDetails(individual);
            if (individual.getFitness() > bestAmongAllGenerations.getFitness()) {
                bestAmongAllGenerations = individual;
            }

            population = ga.crossover(population);
            population = ga.mutate(population, timetable);
            ga.assignFitness(population, timetable);
            currGeneration++;
        }

        Individual individual = population.getFittestIndividual(0);
        System.out.println("Best of generation number " + (currGeneration - 1) + " :");
        printDetails(individual);
        if (individual.getFitness() > bestAmongAllGenerations.getFitness()) {
            bestAmongAllGenerations = individual;
        }

        System.out.println("\nThe best individual among all " + maxGenerations + " generations is :");
        printDetails(bestAmongAllGenerations);
        System.out.println("Here is the timetable : ");
        System.out.println();
        printTimetable(bestAmongAllGenerations);
    }

    public void printDetails(Individual individual) {
        System.out.println("Chromosome : " + Arrays.toString(individual.getChromosome()));
        System.out.println("Clashes : " + individual.getClashes());
        System.out.println("Fitness : " + individual.getFitness());
        System.out.println();
    }

    public void printTimetable(Individual individual) {
        timetable.createLectures(individual);
        Lecture[] lectures = timetable.getLectures();
        int lectureNumber = 1;

        for (Lecture lecture : lectures) {

            System.out.println("Lecture # " + (lectureNumber++) + " : ");
            System.out.println("Course : " + timetable.getCourse(lecture.getCourseId()).getCourseName());
            System.out.println("Batch : " + timetable.getBatch(lecture.getBatchId()).getBatchId());
            System.out.println("Room number : " + timetable.getRoom(lecture.getRoomId()).getRoomNumber());
            System.out.println("Faculty : " + timetable.getFaculty(lecture.getFacultyId()).getFacultyName());
            System.out.println("Timeslot : " + timetable.getTimeslot(lecture.getTimeslotId()).getTimeslot());
            System.out.println("\n**********************************************\n");

        }
    }
}