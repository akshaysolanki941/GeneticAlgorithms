package TimetableScheduling;

import TimetableScheduling.Models.*;

/**
 * This class is representing an individual having a chromosome and its fitness
 * score.
 * 
 * @author Akshay Solanki
 */

public class Individual {
    private int[] chromosome;
    private double fitness = -1;
    private int clashes = -1;

    public Individual(int chromosomeLength) {
        int chromosome[] = new int[chromosomeLength];

        for (int gene = 0; gene < chromosomeLength; gene++) {
            chromosome[gene] = gene;
        }

        this.chromosome = chromosome;
    }

    public Individual(Timetable timetable) {
        int noOfLectures = timetable.getNoOfLectures();
        int chromosomeLength = noOfLectures * 3;
        int chromosome[] = new int[chromosomeLength];
        int gene = 0;

        for (Batch batch : timetable.getBatches()) {
            for (int courseId : batch.getCourseIds()) {

                int timeslotId = timetable.getRandomTimeslot().getTimeslotId();
                int roomId = timetable.getRandomRoom().getRoomId();
                int facultyId = timetable.getCourse(courseId).getRandomFacultyId();

                chromosome[gene] = timeslotId;
                gene++;

                chromosome[gene] = roomId;
                gene++;

                chromosome[gene] = facultyId;
                gene++;
            }
        }

        this.chromosome = chromosome;
    }

    public Individual(int[] chromosome) {
        this.chromosome = chromosome;
    }

    public int[] getChromosome() {
        return this.chromosome;
    }

    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    public void setGene(int index, int gene) {
        chromosome[index] = gene;
    }

    public int getGene(int index) {
        return chromosome[index];
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return this.fitness;
    }

    public void setClashes(int clashes) {
        this.clashes = clashes;
    }

    public int getClashes() {
        return this.clashes;
    }
}