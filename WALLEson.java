package ifsc.WALLEson;
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
        double largura = getBattleFieldWidth();
        double altura = getBattleFieldHeight();
        double meioX = largura / 2;
        double meioY = altura / 2;

       
        double x = getX();
        double y = getY();

        // Virar o robô "para cima" (0° é o norte)
        turnRight(360 - getHeading());

        // Ir até a parede mais próxima
        if (y < meioY && y < (altura - y) && y < x && y < (largura - x)) {
            // Está mais perto da parede de baixo
			turnRight(180 - getHeading());
			ahead(getY() - 20);
            
        } else if ((altura - y) < meioY && (altura - y) < x && (altura - y) < (largura - x)) {
            // Mais perto da parede de cima
            turnRight(0 - getHeading());
            ahead(altura - getY() - 20);

        } else if (x < meioX && x < (largura - x)) {
            // Mais perto da parede da esquerda
            turnRight(270 - getHeading());
            ahead(getX() - 20);

        } else {
            // Mais perto da parede da direita
            turnRight(90 - getHeading());
            ahead(largura - getX() - 20);
        }
		turnGunRight(90);


		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(1000);
			turnGunRight(90);
			turnGunLeft(180);
			turnGunRight(90);
			
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		double anguloDoInimigo = getHeading() + e.getBearing();
		double giroDoCanhao = anguloDoInimigo - getGunHeading();
		double giroNormalizado = robocode.util.Utils.normalRelativeAngleDegrees(giroDoCanhao);
		turnGunRight(giroNormalizado);
		fire(2);
		
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
