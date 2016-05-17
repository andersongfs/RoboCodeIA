package genetictank;
import robocode.*;

public class GeneticRobot extends AdvancedRobot{
	GeneticAlgorithm ga = GeneticAlgorithm.getInstance();
	
	
	
	/**
	 * run: AIGeneticRobot's default behavior
	 */
	public void run() {
		
			
		while(true) {
			doMove();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) { 
		//double bearing = e.getBearing();
		//turnGunRight(bearing);
		fire(ga.getCurrentChromosome().getBulletPower());
	}
	

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
/*	public void onHitByBullet(HitByBulletEvent e) {
		setTurnLeft(ga.getCurrentChromosome().getNextRotation().intValue());

	}*/
	

	/**
	 * onHitWall: What to do when you hit a wall
	 */
/*	public void onHitWall() {
		back(ga.getCurrentChromosome().getNextMove().intValue());
		setTurnLeft(ga.getCurrentChromosome().getNextRotation().intValue());
	}*/
	
	public void onRoundEnded(RoundEndedEvent e){
		ga.roundEnded();
	}
	
	public void doMove(){
		setAhead(ga.getCurrentChromosome().getNextMove().intValue());
		setTurnRight(ga.getCurrentChromosome().getNextRotation().intValue());
		waitFor(new MoveCompleteCondition(this));
		
	}
	
}
