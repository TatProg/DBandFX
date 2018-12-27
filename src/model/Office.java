package model;

import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Office {
    private String restaurant;
    private String place;
    private int members;

    public Office(String restaurant, String place, int members) {
        this.restaurant = restaurant;
        this.place = place;
        this.members = members;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public static void WriteData(ObservableList<Office> purchases, String root) throws IOException {
        File file = new File(root);
        if (!file.exists()) {
            file.createNewFile();

            try (FileWriter writer = new FileWriter(file.getAbsoluteFile())) {
                StringBuilder data = new StringBuilder();
                for (Office purchases1 : purchases) {
                    data.append(purchases1.toString() + "\n");
                }
                writer.write(data.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (FileWriter writer = new FileWriter(file)) {
                StringBuilder data = new StringBuilder();
                for (Office purchases1 : purchases) {
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
        return restaurant + " * " + place + " * " + members;
    }
}
