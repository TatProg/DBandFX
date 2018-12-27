package model;

import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class District {
    private String restaurant;
    private String district;

    public District(String restaurant, String district) {
        this.restaurant = restaurant;
        this.district = district;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public static void WriteData(ObservableList<District> purchases, String root) throws IOException {
        File file = new File(root);
        if (!file.exists()) {
            file.createNewFile();

            try (FileWriter writer = new FileWriter(file.getAbsoluteFile())) {
                StringBuilder data = new StringBuilder();
                for (District purchases1 : purchases) {
                    data.append(purchases1.toString() + "\n");
                }
                writer.write(data.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (FileWriter writer = new FileWriter(file)) {
                StringBuilder data = new StringBuilder();
                for (District purchases1 : purchases) {
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
        return restaurant + ", " + district;
    }
}
