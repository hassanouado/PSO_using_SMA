public class Particle {
    double[] position;
    double[] velocity;
    double[] personalBestPosition;
    double personalBestFitness;
    public Particle() {
        this.position = new double[1];
        this.velocity = new double[1];
        this.personalBestPosition = new double[1];
        this.personalBestFitness = Double.MAX_VALUE;
    }


    public void updateParticle(double[] globalBestPosition) {
        // Mettre à jour la vitesse
        double inertiaWeight = 0.7;
        double cognitiveWeight = 1.4;
        double socialWeight = 1.4;
        double learningRate = 0.5;

        for (int i = 0; i < position.length; i++) {
            velocity[i] = inertiaWeight * velocity[i]
                    + cognitiveWeight * Math.random() * (personalBestPosition[i] - position[i])
                    + socialWeight * Math.random() * (globalBestPosition[i] - position[i]);

            position[i] += learningRate * velocity[i];
        }
    }

    public double evaluateFitness() {
        double x = position[0];
        return x * x;
    }
}