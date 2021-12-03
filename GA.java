import java.util.ArrayList;
import java.util.Comparator;

public class GA {

    public void run(){
        boolean found = false;
        int generation = 0;
        Population population;
        population = Utilities.createPopulation();

        while(!found){
            population.sort();
            // checking whether the top chromosome is the output
            if(population.get(0).getFitness() <= 0)
            {
                break;
            }

            // Performing elitism
            // transferring x% of the previous generation to the
            population.performElitism();

            // creating the remaining population by crossover and mutation
            for (int i=0; i<Utilities.POPULATION*(1-Utilities.ELITISM_RATE); i++){
                /**
                 * Selection chromosomeSelection = getSelection()
                 * population.setSelection(chromosomeSelection)
                 *
                 */
                Selection chromosomeSelection = new RouletteWheelSelection(population);
                population.setSelection(chromosomeSelection);
                Chromosome firstParent = population.select();
                Chromosome secondParent = population.select();
                Chromosome child = firstParent.crossover(secondParent);
                population.add(child);
            }
            //replacing old population with new population
            System.out.println("Generation: " + generation + " String: " + population.get(0).chromosome +
                    " Fitness: " + population.get(0).getFitness());
            generation++;
        }

        System.out.println("Generation: " + generation + " String: " + population.get(0).chromosome +
                " Fitness: " + population.get(0).getFitness());
    }
}
