import java.util.Arrays;
import java.util.Random;


    public class PSO {
        private int swarmSize;
        private int maxIterations;
        private double[] globalBestPosition;
        private double globalBestFitness;
        private Particle[] particles;

        public PSO(int swarmSize, int maxIterations) {
            this.swarmSize = swarmSize;
            this.maxIterations = maxIterations;
            this.globalBestPosition = new double[1]; // 1 dimension pour cet exemple
            this.globalBestFitness = Double.MAX_VALUE;
            this.particles = new Particle[swarmSize];
        }

        public void runPSO() {
            initializeSwarm();

            for (int iteration = 0; iteration < maxIterations; iteration++) {
                for (Particle particle : particles) {
                    double fitness = particle.evaluateFitness();

                    if (fitness < particle.personalBestFitness) {
                        particle.personalBestFitness = fitness;
                        particle.personalBestPosition = particle.position.clone();
                    }

                    if (fitness < globalBestFitness) {
                        globalBestFitness = fitness;
                        globalBestPosition = particle.position.clone();
                    }
                }

                for (Particle particle : particles) {
                    particle.updateParticle(globalBestPosition);
                }
            }

            System.out.println("Meilleure position : " + Arrays.toString(globalBestPosition));
            System.out.println("Meilleure fitness : " + globalBestFitness);
        }

        private void initializeSwarm() {
            Random random = new Random();

            for (int i = 0; i < swarmSize; i++) {
                Particle particle = new Particle();

                for (int j = 0; j < particle.position.length; j++) {
                    particle.position[j] = random.nextDouble() * 10 - 5;
                    particle.velocity[j] = random.nextDouble() * 10 - 5;
                    particle.personalBestPosition[j] = particle.position[j];
                }

                particles[i] = particle;
            }
        }
    }

