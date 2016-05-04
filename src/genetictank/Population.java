package genetictank;

import java.util.ArrayList;
import java.util.List;

public class Population {
	private List<Chromosome> population;
	private int populationSize;

	public Population(int populationSize){
		setPopulation(new ArrayList<Chromosome>());
		setPopulationSize(populationSize);
	}

	public List<Chromosome> getPopulation() {
		return population;
	}

	public void setPopulation(List<Chromosome> population) {
		this.population = population;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public void addChromosome(Chromosome c){
		population.add(c);
	}

	public Chromosome get(int indexOfChromosome){
		if(indexOfChromosome < populationSize){
			return population.get(indexOfChromosome);
		}
		return null;
	}


}
