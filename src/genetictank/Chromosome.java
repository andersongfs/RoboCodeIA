package genetictank;

import java.util.List;

public class Chromosome implements Comparable<Chromosome> {

	private int id;
	private int fitness;

	private List<Double> rotations;
	private List<Double> distances;
	private int nextRotation;
	private int nextMove;

	private double bulletPower;

	public Chromosome(int id, List<Double> rotations, List<Double> moves, double bulletPower) {
		setId(id);
		setRotations(rotations);
		setDistances(moves);
		setBulletPower(bulletPower);
		this.fitness = 0;
		this.nextRotation = 0;
		this.nextMove = 0;
	}
	public Chromosome(int id) {
		setId(id);
		this.fitness = 0;
		this.nextRotation = 0;
		this.nextMove = 0;
	}

	public Double getNextRotation(){
		if(nextRotation == rotations.size()){
			nextRotation = 0;
		}
		Double aux = rotations.get(nextRotation);
		nextRotation++;
		return aux;
	}

	public Double getNextMove(){
		
		if(nextMove == distances.size()){
			nextMove = 0;
		}
		Double aux = distances.get(nextMove);
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
		return distances;
	}

	public void setDistances(List<Double> newDistances) {
		this.distances = newDistances;
	}

	@Override
	public int compareTo(Chromosome c) {
		if (fitness < c.fitness)
			return -1;
		if (fitness > c.fitness)
			return 1;
		return 0;
	}

	public void setId(int id) {
		this.id = id;
	}

}
