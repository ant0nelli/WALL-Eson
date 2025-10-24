import java.util.Scanner;

public class App {   
    public static void main(String[] args) throws Exception {
        
    public void run() {

        while (true) {
            if (inimigo != null) {
                // Comportamento de perseguição (simples)
                turnGunRight(360); // Gira o radar para encontrar o inimigo
            } else {
                // Comportamento padrão: procurar
                turnGunRight(360);
            }
        }
    }

    // Chamado quando o robô detecta outro robô
    public void onScannedRobot(ScannedRobotEvent e) {
        // Se o inimigo foi definido ou se é o primeiro robô escaneado
        if (inimigo == null || e.getName().equals(inimigo)) {
            inimigo = e.getName();
            
            // Calcula o ângulo para mirar no inimigo
            double absoluteBearing = getHeading() + e.getBearing();
            double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());

            // Gira o canhão para o inimigo
            turnGunRight(bearingFromGun);

            // Se o canhão estiver apontado, atira com força 3
            if (getGunHeat() == 0 && Math.abs(bearingFromGun) < 10) {
                fire(3);
            }

            // Move-se em direção ao inimigo (comportamento de perseguição)
            if (e.getDistance() > 150) {
                ahead(e.getDistance() / 2);
            } else {
                back(50);
            }
        }
    }

    // Chamado quando o robô é atingido por um tiro
    public void onHitByBullet(HitByBulletEvent e) {
        // Define o atirador como o inimigo
        inimigo = e.getName();
        
        // Tenta se mover para desviar
        double bearing = e.getBearing();
        turnRight(-bearing);
        ahead(100);
    }

    // Chamado quando o robô colide com outro robô
    public void onHitRobot(HitRobotEvent e) {
        // Define o robô colidido como o inimigo
        inimigo = e.getName();
        
        // Atira com força máxima e se afasta
        fire(3);
        back(100);
    }

    // Chamado quando um robô morre
    public void onRobotDeath(RobotDeathEvent e) {
        // Se o inimigo morrer, limpa a variável
        if (e.getName().equals(inimigo)) {
            inimigo = null;
        }
    }
    
}
}