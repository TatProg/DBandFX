package model;

import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Pizza {
    private String restaurant;
    private String name;

    public Pizza(String restaurant, String name) {
        this.restaurant = restaurant;
        this.name = name;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void WriteData(ObservableList<Pizza> purchases, String root) throws IOException {
        File file = new File(root);
        if (!file.exists()) {
            file.createNewFile();

            try (FileWriter writer = new FileWriter(file.getAbsoluteFile())) {
                StringBuilder data = new StringBuilder();
                for (Pizza purchases1 : purchases) {
                    data.append(purchases1.toString() + "\n");
                }
                writer.write(data.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (FileWriter writer = new FileWriter(file)) {
                StringBuilder data = new StringBuilder();
                for (Pizza purchases1 : purchases) {
                    data.append(purchases1.toString() + "\n");
                }
                writer.write(data.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public String toString() {
        return restaurant + ", " + name;
    }
}
