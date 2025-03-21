import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TrainingSet {
    // file where the training set was saved
    String fileName;

    ArrayList<Vector> trainingVectors;
    ArrayList<String> classes;

    public TrainingSet(String fileName) {
        this.fileName = fileName;
        trainingVectors = new ArrayList<>();
        classes = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                // vector components
                double[] components = new double[parts.length - 1];
                for (int i = 0; i < components.length; i++) {
                    components[i] = Double.parseDouble(parts[i]);
                }

                // class
                String className = parts[parts.length - 1];
                if (!classes.contains(className)) classes.add(className);

                trainingVectors.add(new Vector(components, className));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error reading file.");
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Vector> getTrainingVectors() {
        return trainingVectors;
    }

    public ArrayList<String> getClasses() {
        return classes;
    }
}