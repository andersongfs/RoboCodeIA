package genetictank;

import java.util.List;

public class Chromosome implements Comparable<Chromosome> {
	
	private int id;
	private int fitness;
	
	private List<Double> rotations;
	private List<Double> distances;
	private double bulletPower;
	
	public Chromosome(int id) {
		this.id = id;
		fitness = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public List<Double> getDistances() {
		return distances;
	}
	
	public void setDistances(List<Double> moveDistance) {
		this.distances = moveDistance;
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
	
	public List<Double> getRotations() {
		return rotations;
	}
	
	public void setRotations(List<Double> moveRotation) {
		this.rotations = moveRotation;
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
