import java.util.Arrays;

public class Vector {
    public double[] components;
    public String className;

    public Vector(double[] components, String className) {
        this.components = components;
        this.className = className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "components=" + Arrays.toString(components) +
                ", className='" + className + '\'' +
                '}';
    }
}