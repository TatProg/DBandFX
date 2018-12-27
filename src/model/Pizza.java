package model;

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
}
