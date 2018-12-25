package model;

public class Pizza {
    private String restaurant;
    private String name;
    private int weight;

    public Pizza(String restaurant, String name, int weight) {
        this.restaurant = restaurant;
        this.name = name;
        this.weight = weight;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
