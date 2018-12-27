package model;

import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Quantity {
    private String restaurant;
    private int quantity;

    public Quantity(String restaurant, int quantity) {
        this.restaurant = restaurant;
        this.quantity = quantity;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static void WriteData(ObservableList<Quantity> purchases, String root) throws IOException {
        File file = new File(root);
        if (!file.exists()) {
            file.createNewFile();

            try (FileWriter writer = new FileWriter(file.getAbsoluteFile())) {
                StringBuilder data = new StringBuilder();
                for (Quantity purchases1 : purchases) {
                    data.append(purchases1.toString() + "\n");
                }
                writer.write(data.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (FileWriter writer = new FileWriter(file)) {
                StringBuilder data = new StringBuilder();
                for (Quantity purchases1 : purchases) {
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
        return restaurant + ", " + quantity;
    }
}
