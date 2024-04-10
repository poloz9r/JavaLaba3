import java.io.*;
import java.util.List;
import java.util.ArrayList;
public class DataManager {
    private String fileName;

    public DataManager(String fileName) {
        this.fileName = fileName;
    }



    public List<Glucometer> readFromFile() {
        List<Glucometer> glucometers = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            glucometers = (List<Glucometer>) ois.readObject();
            System.out.println("Данные успешно загружены из файла " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при чтении данных из файла " + fileName + ": " + e.getMessage());
        }
        return glucometers;
    }
    public void saveToFile(List<Glucometer> glucometers) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(glucometers);
            System.out.println("Данные успешно сохранены в файл " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных в файл " + fileName + ": " + e.getMessage());
        }
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
