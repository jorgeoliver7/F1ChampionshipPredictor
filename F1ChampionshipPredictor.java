import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class F1ChampionshipPredictor {
    // Clase para representar a un piloto
    static class Driver {
        String name;
        String team;
        int pastPerformance; // Puntos de 2024 como base
        double teamStrength; // Fuerza del equipo (0.0 a 1.0)
        double consistency;  // Consistencia (0.0 a 1.0)

        public Driver(String name, String team, int pastPerformance, double teamStrength, double consistency) {
            this.name = name;
            this.team = team;
            this.pastPerformance = pastPerformance;
            this.teamStrength = teamStrength;
            this.consistency = consistency;
        }

        // Calcular probabilidad de éxito
        public double calculateSuccessProbability(Random rand) {
            // Fórmula: (rendimiento pasado * 0.4) + (fuerza equipo * 0.4) + (consistencia * 0.15) + (factor aleatorio * 0.05)
            double baseScore = (pastPerformance * 0.4) + (teamStrength * 1000 * 0.4) + (consistency * 100 * 0.15);
            double randomFactor = rand.nextDouble() * 100 * 0.05; // Aleatoriedad entre 0 y 5%
            return baseScore + randomFactor;
        }
    }

    public static void main(String[] args) {
        // Crear pilotos con datos hipotéticos basados en 2024 y suposiciones para 2025
        Map<String, Driver> drivers = new HashMap<>();
        drivers.put("Verstappen", new Driver("Max Verstappen", "Red Bull", 408, 0.85, 0.95));
        drivers.put("Norris", new Driver("Lando Norris", "McLaren", 337, 0.95, 0.85));
        drivers.put("Leclerc", new Driver("Charles Leclerc", "Ferrari", 319, 0.90, 0.90));
        drivers.put("Hamilton", new Driver("Lewis Hamilton", "Ferrari", 293, 0.90, 0.80));
        drivers.put("Piastri", new Driver("Oscar Piastri", "McLaren", 251, 0.95, 0.75));
        drivers.put("Russell", new Driver("George Russell", "Mercedes", 235, 0.80, 0.85));

        // Simulación
        Random rand = new Random();
        String winner = "";
        double highestScore = -1;

        System.out.println("Predicción del Campeonato de F1 2025:");
        System.out.println("-------------------------------------");

        for (Driver driver : drivers.values()) {
            double score = driver.calculateSuccessProbability(rand);
            System.out.printf("%s (%s): %.2f puntos%n", driver.name, driver.team, score);

            if (score > highestScore) {
                highestScore = score;
                winner = driver.name;
            }
        }

        System.out.println("-------------------------------------");
        System.out.println("Piloto con más posibilidades de ganar: " + winner);
    }
}