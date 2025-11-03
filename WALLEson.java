package ifsc.WALLEson;
import robocode.*;

public class WALLEson extends Robot {

    public void run() {
        double largura = getBattleFieldWidth();
        double altura = getBattleFieldHeight();
        double meioX = largura / 2;
        double meioY = altura / 2;

        double x = getX();
        double y = getY();

        
        turnRight(360 - getHeading());

       
        if (y < meioY && y < (altura - y) && y < x && y < (largura - x)) {
            turnRight(180 - getHeading());
            ahead(getY() - 20);

        } else if ((altura - y) < meioY && (altura - y) < x && (altura - y) < (largura - x)) {
            turnRight(0 - getHeading());
            ahead(altura - getY() - 20);

        } else if (x < meioX && x < (largura - x)) {
            turnRight(270 - getHeading());
            ahead(getX() - 20);

        } else {
            turnRight(90 - getHeading());
            ahead(largura - getX() - 20);
        }

        turnGunRight(90);

        
        while (true) {
            ahead(1000);
            turnGunRight(90);
            turnGunLeft(180);
            turnGunRight(90);
        }
    }

   
    public void onScannedRobot(ScannedRobotEvent e) {
      
        double anguloParaInimigo = getHeading() + e.getBearing() - getGunHeading();

     
        if (anguloParaInimigo > 180) anguloParaInimigo -= 360;
        if (anguloParaInimigo < -180) anguloParaInimigo += 360;

      
        turnGunRight(anguloParaInimigo);

      
        double distancia = e.getDistance();
        double forcaTiro = 3.0;
        if (distancia > 400) forcaTiro = 1.5;
        else if (distancia > 200) forcaTiro = 2.0;

      
        fire(forcaTiro);
    }

    public void onHitByBullet(HitByBulletEvent e) {
       
    }
}

