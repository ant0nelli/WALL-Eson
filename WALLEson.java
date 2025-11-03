package ifsc.WALLEson;
import robocode.*;

// WALLEson - um robô simples e funcional
public class WALLEson extends Robot {

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

        // Loop principal
        while (true) {
            ahead(1000);
            turnGunRight(90);
            turnGunLeft(180);
            turnGunRight(90);
        }
    }

    // Aumenta a precisão dos tiros
    public void onScannedRobot(ScannedRobotEvent e) {
        // Calcula o ângulo entre o robô e o inimigo
        double anguloParaInimigo = getHeading() + e.getBearing() - getGunHeading();

        // Normaliza o ângulo para -180° a 180°
        if (anguloParaInimigo > 180) anguloParaInimigo -= 360;
        if (anguloParaInimigo < -180) anguloParaInimigo += 360;

        // Gira o canhão na direção do inimigo
        turnGunRight(anguloParaInimigo);

        // Ajusta a força do tiro conforme a distância
        double distancia = e.getDistance();
        double forcaTiro = 3.0;
        if (distancia > 400) forcaTiro = 1.5;
        else if (distancia > 200) forcaTiro = 2.0;

        // Dispara com precisão
        fire(forcaTiro);
    }

    public void onHitByBullet(HitByBulletEvent e) {
        // Por enquanto, não reage — mas podemos adicionar algo simples aqui depois
    }
}
