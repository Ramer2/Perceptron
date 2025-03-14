public class Perceptron {
    static double[] weights;
    static double bias;
    static double learningRate;

    public static int predict(double[] testVector) {
        double product = 0;
        for (int i = 0; i < testVector.length; i++)
            product += testVector[i] * weights[i];

        if (product - bias >= 0) return 1;
        else return 0;
    }

    public static void updateWeights(double[] trainVector, int label) {
        int prediction = predict(trainVector);
        for (int j = 0; j < weights.length; j++) {
            weights[j] += learningRate * (label - prediction) * trainVector[j];
        }

        bias += learningRate * (label - prediction);
    }

    public static void main(String[] args) {



    }
}
