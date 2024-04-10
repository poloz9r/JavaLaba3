// Glucometer.java
import java.io.Serializable;
public class Glucometer implements Serializable {
    private static final long serialVersionUID = 1L; //

    private String model;

    private double accuracy;

    // Конструктор по умолчанию
    public Glucometer() {
        this.model = "DefaultModel";
        this.accuracy = 0.0;
    }

    // Конструктор с параметрами
    public Glucometer(String model, double accuracy) {
        this.model = model;
        this.accuracy = accuracy;
    }

    // Геттер для модели
    public String getModel() {
        return model;
    }

    // Сеттер для модели
    public void setModel(String model) {
        this.model = model;
    }

    // Геттер для точности
    public double getAccuracy() {
        return accuracy;
    }

    // Сеттер для точности
    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    // Увеличение точности на указанное значение
    public void increaseAccuracy(double value) {
        accuracy += value;
    }

    // Уменьшение точности на указанное значение
    public void decreaseAccuracy(double value) {
        accuracy -= value;
    }
}
