import java.util.Arrays;
import java.util.Scanner;

// Iris-versicolor = 0, Iris-virginica = 1
public class Perceptron {
    static double[] weights;
    static double bias;
    static double learningRate;

    public static int predict(Vector testVector) {
        double product = 0;
        for (int i = 0; i < testVector.components.length; i++)
            product += testVector.components[i] * weights[i];

        if (product - bias >= 0) return 1;
        else return 0;
    }

    public static void updateWeights(Vector trainVector, int label) {
        int prediction = predict(trainVector);
        for (int j = 0; j < weights.length; j++) {
            weights[j] += learningRate * (label - prediction) * trainVector.components[j];
        }

        bias -= learningRate * (label - prediction);
    }

    public static void learn(TrainingSet trainingSet, int epochs) {
        for (int i = 0; i < epochs; i++) {
            for (Vector vector : trainingSet.trainingVectors) {
                int className = vector.className.equals("Iris-versicolor") ? 0 : 1;
                updateWeights(vector, className);
            }
        }
    }

    public static String testVector(Vector testVector) {
        return predict(testVector) == 0 ? "Iris-versicolor" : "Iris-virginica";
    }

    public static String testTestingSet(TestingSet testingSet) {
        int correct = 0;
        int total = testingSet.testingVectors.size();
        for (Vector vector : testingSet.getTestingVectors()) {
            if (vector.className.equals(testVector(vector))) correct++;
        }

        double accuracy = ((double) correct / total) * 100;
        return String.format("Result accuracy: %.2f%%", accuracy);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of test epochs for training: ");
        int epochs = sc.nextInt();

        String fileName;
//        fileName = sc.nextLine();
        fileName = "./src/main/java/perceptron.data";

        TrainingSet trainingSet = new TrainingSet(fileName);
        TestingSet testingSet = new TestingSet(fileName);
        weights = new double[trainingSet.trainingVectors.getFirst().components.length];
        bias = 0;
        learningRate = 0;

        // random bias and initial weights
        bias = (Math.random() * 1) + 0;
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (Math.random() * 1) + 0;
        }

        System.out.print("Learning rate: ");
        learningRate = sc.nextDouble();

        learn(trainingSet, epochs);

        System.out.println("Learned " + epochs + " epochs with " + weights.length + " weights");
        System.out.println(Arrays.toString(weights));

        System.out.println(testTestingSet(testingSet));
    }
}
