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
		fire(ga.getCurrentChromosome().getBulletPower());
	}
	

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
/*	public void onHitByBullet(HitByBulletEvent e) {
		setTurnRight(ga.getCurrentChromosome().getNextRotation().intValue());
		ahead(ga.getCurrentChromosome().getNextMove().intValue());

	}
	*/

	/**
	 * onHitWall: What to do when you hit a wall
	 */
/*	public void onHitWall(HitWallEvent e) {
		turnRight(e.getBearing());
		doNothing();
	}*/

	public void onRoundEnded(RoundEndedEvent e){
		ga.roundEnded();
	}
	
	public void onWin(WinEvent event){
		ga.roundWon();
	}
	
	public void doMove(){
		setAhead(ga.getCurrentChromosome().getNextMove().intValue());
		setTurnRight(ga.getCurrentChromosome().getNextRotation().intValue());
		waitFor(new MoveCompleteCondition(this));
		
	}
	
}
