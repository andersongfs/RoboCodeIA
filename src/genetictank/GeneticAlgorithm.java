package genetictank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm{
	
	private static int POPULATION_SIZE = 20;
	private static int RENEW_RATE = 15;
	private static int MAX_NUMBER_OF_ROUNDS = 5;
	private static int MUTATION_RATIO = 20; 
	private static int MAX_GENES = 6;
	private static int MIN_BULLET_POWER = 1;
	private static int MAX_BULLET_POWER = 3;
	private static int BULLET_POWER_PRECISION = 10;
	private static List<Chromosome> chromosomes;
	private static List<Double> partilStatistics;
	private static List<Double> totalStatistics;
	private static int initialPosition;
	private static int round;
	private static int totalNumberOfWins;
	private static int totalNumberOfRounds;
	private static int partialNumberOfWins;
	private static int partialNumberOfRounds;
	
	public static void start(GeneticRobot robot) {
		if (chromosomes == null) {
			generateRandomPopulation();			
			initialPosition = 0;
			totalNumberOfRounds = 0;
			totalNumberOfWins = 0;
			partialNumberOfRounds = 0;
			partialNumberOfWins = 0;
			round = 0;
			totalStatistics = new ArrayList<Double>();
			partilStatistics = new ArrayList<Double>();
		}
		if (round == MAX_NUMBER_OF_ROUNDS) {
			initialPosition++;
			round = 0;
		}
		if (initialPosition == POPULATION_SIZE) {
			initialPosition = 0;
			round = 0;
			renewGeneration();
			partilStatistics.add((((double)partialNumberOfWins)/partialNumberOfRounds)*100);
			totalStatistics.add((((double)totalNumberOfWins)/totalNumberOfRounds)*100);
			partialNumberOfWins = 0;
			partialNumberOfRounds = 0;
		}
		showResults();
		System.out.println("The battle has started with the robot of number" + initialPosition + " out of " + chromosomes.size() + "! Round number: " + round);
		robot.chromosome = chromosomes.get(initialPosition);
	}
	

	private static Chromosome generateRandomChromosome(int id) {
		Chromosome chromosome = new Chromosome(id);
		
		List<Double> moveRotation = new ArrayList<Double>();
		List<Double> moveDistance = new ArrayList<Double>();
		
		int moves = randomInt(1,MAX_GENES);
		
		for(int i = 0; i < moves; i++) {
			moveRotation.add((double)randomInt(0, 180));
			moveDistance.add((double)randomInt(100, 500));
		}
		
		chromosome.setRotations(moveRotation);
		chromosome.setDistances(moveDistance);
		
		int begin = MIN_BULLET_POWER * BULLET_POWER_PRECISION;
		int end = MAX_BULLET_POWER * BULLET_POWER_PRECISION;
		chromosome.setBulletPower(((double)randomInt(begin, end)) / BULLET_POWER_PRECISION);
		
		return chromosome;
	}
	
	private static void generateRandomPopulation() {
		chromosomes = new ArrayList<Chromosome>();		
		for (int i = 0; i < POPULATION_SIZE; i++)
			chromosomes.add(generateRandomChromosome(i));
	}

	
	public static void onRobotWin(Chromosome chromosome) {
		chromosome.increaseFitness();
		totalNumberOfWins++;
		partialNumberOfWins++;
	}
	
	public static void onRoundEnded(Chromosome chromosome) {
		round++;
		totalNumberOfRounds++;
		partialNumberOfRounds++;
	}
	
	public static void onBattleEnded(Chromosome chromosome) {
		System.out.println("========== Relative Statistics ==========");
		for(int i = 0; i < partilStatistics.size(); i++) {
			System.out.println(partilStatistics.get(i));
		}
		System.out.println("========== Absolute Statistics ==========");
		for(int i = 0; i < totalStatistics.size(); i++) {
			System.out.println(totalStatistics.get(i));
		}
	}
	
	private static void renewGeneration() {
		Collections.sort(chromosomes);
		for(int i = 0; i < RENEW_RATE; i++) {
			int partition = randomInt(0, 1);
			int beggining = i;
			if(partition == 1)
				beggining = RENEW_RATE;
			
			Chromosome chromosome = chromosomes.get(i);
			int parent1 = randomInt(beggining, POPULATION_SIZE-1);
			int parent2;			
			if(parent1 == POPULATION_SIZE-1) {
				parent2 = POPULATION_SIZE-2;
			} else {
				parent2 = randomInt(parent1+1, POPULATION_SIZE-1);
			}
			int geneSize = chromosomes.get(parent1).getDistances().size();
			int splitPoint = randomInt((geneSize+1)/2, Math.min(geneSize, MAX_GENES/2));
			
			crossOver(geneSize,splitPoint,parent1,parent2,chromosome);
		}
		
		for (int i = 0; i < POPULATION_SIZE; i++) {
			chromosomes.get(i).setFitness(0);
		}
	}
	
	private static void crossOver(int geneSize, int splitPoint,int parent1, int parent2,Chromosome chromosome){
		List<Double> moveRotation = new ArrayList<Double>();
		List<Double> moveDistance = new ArrayList<Double>();
		for(int j = 0; j < splitPoint; j++) {
			double mr = chromosomes.get(parent1).getRotations().get(j);
			double md = chromosomes.get(parent1).getDistances().get(j);
			
			boolean hasMutation = randomInt(1, MUTATION_RATIO) == 1;
			if(hasMutation) {
				mr += randomInt(-45,45);
				md += randomInt(-100,100);
			}
			moveRotation.add(mr);
			moveDistance.add(md);
		}
		
		geneSize = chromosomes.get(parent2).getDistances().size();
		splitPoint = randomInt((geneSize+1)/2, Math.min(geneSize, MAX_GENES/2));
		
		for(int j = geneSize-splitPoint; j < geneSize; j++) {
			double mr = chromosomes.get(parent2).getRotations().get(j);
			double md = chromosomes.get(parent2).getDistances().get(j);
			
			boolean hasMutation = randomInt(1, MUTATION_RATIO) == 1;
			if(hasMutation) {
				mr += randomInt(-20,20);
				md += randomInt(-50,50);
			}
			
			moveRotation.add(mr);
			moveDistance.add(md);
		}
		
		chromosome.setRotations(moveRotation);
		chromosome.setDistances(moveDistance);
		double totalBulletPower = chromosomes.get(parent1).getBulletPower() +
							chromosomes.get(parent2).getBulletPower();
		chromosome.setBulletPower(totalBulletPower / 2);
	}
	
	private static void showResults() {
		if(partialNumberOfRounds > 0)
			System.out.println("Relative Win: " + ((((double)partialNumberOfWins)/partialNumberOfRounds)*100) + "%");
		if(totalNumberOfRounds > 0)
			System.out.println("Absolute Win: " + ((((double)totalNumberOfWins)/totalNumberOfRounds)*100) + "%");
		
		System.out.println("relativeRounds: " + partialNumberOfRounds);
		System.out.println("relativeWins: " + partialNumberOfWins);
		System.out.println("absoluteRounds: " + totalNumberOfRounds);
		System.out.println("absoluteWins: " + totalNumberOfWins);
	}
	
	private static int randomInt(int start, int end) {
		Random rand = new Random();
		return rand.nextInt(end - start + 1) + start;
	}
}
