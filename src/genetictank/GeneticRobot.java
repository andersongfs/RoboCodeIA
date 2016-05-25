package genetictank;

import robocode.AdvancedRobot;
import robocode.BattleEndedEvent;
import robocode.MoveCompleteCondition;
import robocode.RoundEndedEvent;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

/**
 * GeneticRobot - A robot with genetic movement.
 */
public class GeneticRobot extends AdvancedRobot {
	
	private int move;
	
	Chromosome chromosome;
	
	public void run() {
		GeneticAlgorithm.start(this);
		move = 0;
		while (true) {
			doMove();
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(chromosome.getBulletPower());
	}
	
	public void onWin(WinEvent e) {
		GeneticAlgorithm.onRobotWin(chromosome);
	}
	
	public void onRoundEnded(RoundEndedEvent e) {
		GeneticAlgorithm.onRoundEnded(chromosome);
	}
	
	public void onBattleEnded(BattleEndedEvent e) {
		GeneticAlgorithm.onBattleEnded(chromosome);
	}
	
	private void doMove() {
		setAhead(chromosome.getDistances().get(move));
		setTurnRight(chromosome.getRotations().get(move));
		waitFor(new MoveCompleteCondition(this));
		
		move++;
		if(move >= chromosome.getRotations().size()) {
			move = 0;
		}
	}
}
