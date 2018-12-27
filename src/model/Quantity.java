package model;

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
}
