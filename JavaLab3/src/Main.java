import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        List<Glucometer> glucometers = new ArrayList<>();
        DataManager dataManager = new DataManager("soxrdannie.dat");

        do {
            System.out.println("\nМеню:");
            System.out.println("1. Создать объекты");
            System.out.println("2. Отобразить значения свойств объектов");
            System.out.println("3. Изменить свойства объектов");
            System.out.println("4. Добавить объекты в массив");
            System.out.println("5. Удалить объекты из массива");
            System.out.println("6. Вычислить самую лучшую точность");
            System.out.println("7. Увеличить точность глюкометра");
            System.out.println("8. Уменьшить точность глюкометра");
            System.out.println("9. Записать данные в файл");
            System.out.println("10. Прочитать данные из файла");
            System.out.println("11. Изменить название файла");
            System.out.println("12. Выход");
            System.out.print("Выберите действие: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createObjects(glucometers);
                    break;
                case 2:
                    displayObjects(glucometers);
                    break;
                case 3:
                    modifyObjectProperties(glucometers);
                    break;
                case 4:
                    addObjectToArray(glucometers);
                    break;
                case 5:
                    removeObjectFromArray(glucometers);
                    break;
                case 6:
                    calculateBestAccuracy(glucometers);
                    break;
                case 7:
                    increaseAccuracy(glucometers);
                    break;
                case 8:
                    decreaseAccuracy(glucometers);
                    break;
                case 9:
                    dataManager.saveToFile(glucometers);
                    break;
                case 10:
                    glucometers = dataManager.readFromFile();
                    break;
                case 11:
                    System.out.print("Введите новое название файла: ");
                    String newFileName = scanner.next();
                    dataManager.setFileName(newFileName);
                    System.out.println("Имя файла успешно изменено на " + newFileName);
                    glucometers = dataManager.readFromFile();
                    break;
                case 12:
                    System.out.println("Программа завершена.");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    break;
            }

        } while (choice != 12);
    }

    public static void createObjects(List<Glucometer> glucometers) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество глюкометров для создания: ");
        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            System.out.println("Введите модель для глюкометра " + (i + 1) + ": ");
            String model = scanner.next();
            double accuracy;
            do {
                System.out.println("Введите точность для глюкометра " + (i + 1) + ": ");
                accuracy = scanner.nextDouble();
                if (accuracy < 0) {
                    System.out.println("Точность не может быть отрицательной. Пожалуйста, введите положительное значение.");
                }
            } while (accuracy < 0);

            glucometers.add(new Glucometer(model, accuracy));
        }
        System.out.println("Объекты успешно созданы и данные введены.");
    }



    public static void displayObjects(List<Glucometer> glucometers) {
        System.out.println("---------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-10s |\n", "Индекс", "Модель", "Точность");
        System.out.println("---------------------------------------------------");
        for (int i = 0; i < glucometers.size(); i++) {
            Glucometer glucometer = glucometers.get(i);
            System.out.printf("| %-10d | %-20s | %-10.2f |\n", i, glucometer.getModel(), glucometer.getAccuracy());
        }
        System.out.println("---------------------------------------------------");
    }

    public static void modifyObjectProperties(List<Glucometer> glucometers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер объекта для изменения свойств (0-" + (glucometers.size() - 1) + "): ");
        int index = scanner.nextInt();
        if (index >= 0 && index < glucometers.size()) {
            System.out.print("Введите новую модель: ");
            String newModel = scanner.next();
            double newAccuracy;
            do {
                System.out.print("Введите новую точность: ");
                newAccuracy = scanner.nextDouble();
                if (newAccuracy < 0) {
                    System.out.println("Точность не может быть отрицательной. Пожалуйста, введите положительное значение.");
                }
            } while (newAccuracy < 0);

            glucometers.get(index).setModel(newModel);
            glucometers.get(index).setAccuracy(newAccuracy);
            System.out.println("Свойства объекта изменены.");
        } else {
            System.out.println("Неверный индекс объекта или объект не существует.");
        }
    }

    public static void addObjectToArray(List<Glucometer> glucometers) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите модель для нового глюкометра: ");
        String model = scanner.next();
        double accuracy;
        do {
            System.out.println("Введите точность для нового глюкометра: ");
            accuracy = scanner.nextDouble();
            if (accuracy < 0) {
                System.out.println("Точность не может быть отрицательной. Пожалуйста, введите положительное значение.");
            }
        } while (accuracy < 0);

        glucometers.add(new Glucometer(model, accuracy));
        System.out.println("Объект успешно добавлен.");
    }

    public static void removeObjectFromArray(List<Glucometer> glucometers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер объекта для удаления (0-" + (glucometers.size() - 1) + "): ");
        int index = scanner.nextInt();
        if (index >= 0 && index < glucometers.size()) {
            glucometers.remove(index);
            System.out.println("Объект успешно удален.");
        } else {
            System.out.println("Неверный индекс объекта или объект не существует.");
        }
    }

    public static void calculateBestAccuracy(List<Glucometer> glucometers) {
        double bestAccuracy = 0.0;
        for (Glucometer glucometer : glucometers) {
            if (glucometer.getAccuracy() > bestAccuracy) {
                bestAccuracy = glucometer.getAccuracy();
            }
        }
        System.out.println("Самая лучшая точность: " + bestAccuracy);
    }

    public static void increaseAccuracy(List<Glucometer> glucometers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер объекта для увеличения точности (0-" + (glucometers.size() - 1) + "): ");
        int index = scanner.nextInt();
        if (index >= 0 && index < glucometers.size()) {
            System.out.print("Введите значение, на которое нужно увеличить точность: ");
            double value = scanner.nextDouble();
            if (value < 0) {
                System.out.println("Значение не может быть отрицательным. Пожалуйста, введите положительное значение.");
                return;
            }
            glucometers.get(index).increaseAccuracy(value);
            System.out.println("Точность глюкометра увеличена.");
        } else {
            System.out.println("Неверный индекс объекта или объект не существует.");
        }
    }

    public static void decreaseAccuracy(List<Glucometer> glucometers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер объекта для уменьшения точности (0-" + (glucometers.size() - 1) + "): ");
        int index = scanner.nextInt();
        if (index >= 0 && index < glucometers.size()) {
            System.out.print("Введите значение, на которое нужно уменьшить точность: ");
            double value = scanner.nextDouble();
            if (value < 0) {
                System.out.println("Значение не может быть отрицательным. Пожалуйста, введите положительное значение.");
                return;
            }
            glucometers.get(index).decreaseAccuracy(value);
            System.out.println("Точность глюкометра уменьшена.");
        } else {
            System.out.println("Неверный индекс объекта или объект не существует.");
        }
    }
}
