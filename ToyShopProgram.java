import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Toy {
    private String name;
    private double weight;

    public Toy(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
}

class ToyShop {
    private List<Toy> toys;
    private Random randomGenerator;

    public ToyShop() {
        toys = new ArrayList<>();
        randomGenerator = new Random();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public Toy getRandomToy() {
        int index = randomGenerator.nextInt(toys.size());
        return toys.get(index);
    }

    public void saveToysToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Toy toy : toys) {
                writer.write(toy.getName() + "," + toy.getWeight() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadToysFromFile(String filename) {
        toys.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    double weight = Double.parseDouble(parts[1]);
                    Toy toy = new Toy(name, weight);
                    toys.add(toy);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class ToyShopProgram {
    public static void main(String[] args) {
        ToyShop toyShop = new ToyShop();

        // Создаем несколько игрушек и добавляем их в магазин
        toyShop.addToy(new Toy("Машинка", 500));
        toyShop.addToy(new Toy("Кукла", 130));
        toyShop.addToy(new Toy("Конструктор", 420));

        // Сохраняем игрушки в файл
        toyShop.saveToysToFile("toys.txt");

        // Загружаем игрушки из файла
        toyShop.loadToysFromFile("toys.txt");

        // Получаем случайную игрушку из магазина
        Toy randomToy = toyShop.getRandomToy();
        System.out.println("Выигрышная игрушка: " + randomToy.getName() + ", вес: " + randomToy.getWeight());
    }
}