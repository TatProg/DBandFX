package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Weight {
    private String pizzaName;
    private int weight;
    public static ObservableList<Weight> weightObservableList = FXCollections.observableArrayList();

    public Weight(String pizzaName, int weight) {
        this.pizzaName = pizzaName;
        this.weight = weight;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String restaurant) {
        this.pizzaName = restaurant;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "{" +
                "'" + pizzaName + '\'' +
                ", " + weight +
                '}';
    }
}
