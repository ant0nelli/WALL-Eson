package ifsc;
import robocode.*;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html
 
/**
 * WALLEson - a robot by (your name here)
 */
public class WALLEson extends Robot
{
	/** 
	 * run: WALLEson's default behavior
	 */
	public void run() {
		turnRight(180 - getHeading());
		if (getY() > getBattleFieldHeight()){
			ahead(getBattleFieldHeight() - getY());
		}else{
			ahead(getY() - getBattleFieldHeight());
		}
		turnGunLeft(90);

		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(-1000);
			turnGunRight(90);
			turnGunLeft(180);
			turnGunRight(90);
			
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(1);
		
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		
		// Replace the next line with any behavior you would like
	}

	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		turnRight(90);
		
	}	
}
