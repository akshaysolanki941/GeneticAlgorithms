# GeneticAlgorithms
Some NP-hard problems like **Travelling Salesman Problem**, **Timetable Scheduling** using genetic algorithm.

To understand genetic algorithm, we have to first know the basics of genetic evolution. Each generation is better than its preceding generations because of evolution. To produce the healthiest offspring, two fittest parents are to be chosen from the population - this is called crossover. Then on this produced offspring, mutation happens.

To solve problems using genetic algorithms, we make a random population of individuals that can be a potential solution to the problem. Now crossover and mutation are done on this generation till we get the healthiest individual i.e. the individual having the highest fitness score. This fitness score depends upon the type of problem. For example in the TSP, the more is the distance of the route, the less will be the fitness score of that individual because we want the shortest distance route. And the shortest distance route individual will have the highest fitness score. We come more closer to our solution as the generation increases. 
