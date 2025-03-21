import java.io.FileWriter;
import java.io.IOException;

public class PerceptronRunner {
    public static void runAndRecordResults(String fileName, String outputTsvFile) {
        try {
            // Open a FileWriter to write the results to a TSV file
            FileWriter writer = new FileWriter(outputTsvFile);
            writer.append("Epochs\tAccuracy\n"); // Use tab delimiter for TSV

            // Initialize the training and testing sets
            TrainingSet trainingSet = new TrainingSet(fileName);
            TestingSet testingSet = new TestingSet(fileName);

            double learningRate = 0.01;


            // Loop over epochs from 10 to 1000 in increments of 10
            for (int epochs = 10; epochs <= 10000; epochs += 10) {
                // Reset weights and bias for each new epoch
                double[] weights = new double[trainingSet.trainingVectors.getFirst().components.length];
                double bias = (Math.random() * 1) + 0; // random bias

                // Randomize the initial weights
                for (int i = 0; i < weights.length; i++) {
                    weights[i] = (Math.random() * 1) + 0; // random weight
                }

                // Train the model for the current number of epochs
                Perceptron.weights = weights;
                Perceptron.bias = bias;
                Perceptron.learningRate = learningRate;

                Perceptron.learn(trainingSet, epochs);

                // Test the model and get accuracy as decimal value
                String accuracyResult = Perceptron.testTestingSet(testingSet);
                double accuracy = extractDecimalAccuracy(accuracyResult) / 100;

                // Write the result to the TSV file (use \t for tab separation)
                writer.append(String.format("%d\t%.4f\n", epochs, accuracy)); // Write accuracy as decimal
            }

            // Close the writer
            writer.flush();
            writer.close();
            System.out.println("Results written to: " + outputTsvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to extract the decimal accuracy from the result string
    private static double extractDecimalAccuracy(String result) {
        // Extract accuracy from result string (example: "Result accuracy: 0.7532")
        String[] parts = result.split(": ");
        String accuracyStr = parts[1].trim();
        accuracyStr = accuracyStr.substring(0, accuracyStr.length() - 1);
        accuracyStr = accuracyStr.replace(',', '.');
        return Double.parseDouble(accuracyStr); // Return accuracy as a decimal value
    }

    public static void main(String[] args) {
        String fileName = "./src/main/java/perceptron.data";
        String outputTsvFile = "./src/main/java/perceptron_results.tsv"; // Output file as TSV

        // Run and record results
        runAndRecordResults(fileName, outputTsvFile);
    }
}
