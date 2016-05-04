package genetictank;

import java.util.List;

public class Chromosome implements Comparable<Chromosome> {

	private int id;
	private int fitness;

	private List<Double> rotations;
	private List<Double> moves;
	private int nextRotation;
	private int nextMove;

	private double bulletPower;

	public Chromosome(int id) {
		this.id = id;
		fitness = 0;
		nextRotation = 0;
		nextMove = 0;
	}

	public int getNextRotation(){
		if(nextRotation == rotations.size()){
			nextRotation = 0;
		}
		int aux = nextRotation;
		nextRotation++;
		return aux;
	}

	public int getNextMove(){
		if(nextMove == moves.size()){
			nextMove = 0;
		}
		int aux = nextMove;
		nextMove++;
		return aux;
	}

	public int getId() {
		return id;
	}

	public int getFitness() {
		return fitness;
	}

	public void increaseFitness() {
		fitness += 1;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public double getBulletPower() {
		return bulletPower;
	}

	public void setBulletPower(double bulletPower) {
		this.bulletPower = bulletPower;
	}


	public List<Double> getNextRotations() {
		return rotations;
	}

	public void setRotations(List<Double> newRotations) {
		this.rotations = newRotations;
	}

	public List<Double> getDistances() {
		return moves;
	}

	public void setMoveDistance(List<Double> newDistances) {
		this.moves = newDistances;
	}

	@Override
	public int compareTo(Chromosome c) {
		if (fitness < c.fitness)
			return -1;
		if (fitness > c.fitness)
			return 1;
		return 0;
	}

}
