public class LossAndGradient {
    public static void main(String[] args) {
        // Input values
        double[][] inputs = {{1, 0}, {2, 1}, {0, 1}, {-2, 1}};
        // Corresponding output values
        double[] outputs = {1, 9, 1, 7};

        // Initial weights
        double w1 = 0.5;
        double w2 = 0.5;

        // Learning rate
        double learningRate = 0.01;

        // Number of iterations
        int iterations = 1000;

        // Gradient descent
        for (int i = 0; i < iterations; i++) {
            double totalLoss = 0;
            double dw1 = 0;
            double dw2 = 0;

            for (int j = 0; j < inputs.length; j++) {
                double x1 = inputs[j][0];
                double x2 = inputs[j][1];
                double y = outputs[j];

                // Forward pass
                double predictedOutput = w1 * x1 + w2 * x2;

                // Loss function
                double loss = 0.5 * Math.pow(predictedOutput - y, 2);
                totalLoss += loss;

                // Backward pass
                dw1 += (predictedOutput - y) * x1;
                dw2 += (predictedOutput - y) * x2;
            }

            // Update weights
            w1 -= learningRate * dw1 / inputs.length;
            w2 -= learningRate * dw2 / inputs.length;

            // Print loss for every 100 iterations
            if (i % 100 == 0) {
                System.out.println("Iteration " + i + ": Loss = " + totalLoss);
            }
        }

        System.out.println("Final weights: w1 = " + w1 + ", w2 = " + w2);
    }
}
