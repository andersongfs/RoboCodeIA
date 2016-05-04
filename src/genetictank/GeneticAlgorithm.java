package genetictank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {
	/**
	 * Params to geneticAlgorithm
	 */
	private int POPULATION_SIZE = 20;
	private int MIN_BULLET_POWER = 1;
	private int MAX_BULLET_POWER = 3;
	private int CHROMOSOME_PARAMS_SIZE = 9; // QUANTITY OF MOVES AND ROTATIONS THAT A CHROMOSOME MUST HAVE

	private Population population;

	public void generateRandomPopulation(){
		Population p = new Population(POPULATION_SIZE);
		for(int i = 0; i < POPULATION_SIZE; i++){
			p.addChromosome(generateRandomChromosome(i));
		}
	}

	private Chromosome generateRandomChromosome(int id) {
		Chromosome chromosome = new Chromosome(id);
		List<Double> rotations = new ArrayList<Double>();
		List<Double> moves = new ArrayList<Double>();

		for(int i = 0; i < CHROMOSOME_PARAMS_SIZE; i++) {
			rotations.add((double)randomInt(0, 180));
			moves.add((double)randomInt(100, 500));
		}

		chromosome.setRotations(rotations);
		chromosome.setDistances(moves);
		chromosome.setBulletPower(randomInt(MIN_BULLET_POWER, MAX_BULLET_POWER));

		return chromosome;

	}

	private int randomInt(int start, int end) {
		Random rand = new Random();
		return rand.nextInt(end - start + 1) + start;
	}

}
