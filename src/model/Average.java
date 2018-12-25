package model;

public class Average {
    private String restaurant;
    private int averageMembers;
    private float averageWeight;

    public Average(String restaurant, int averageMembers, float averageWeight) {
        this.restaurant = restaurant;
        this.averageMembers = averageMembers;
        this.averageWeight = averageWeight;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public int getAverageMembers() {
        return averageMembers;
    }

    public void setAverageMembers(int averageMembers) {
        this.averageMembers = averageMembers;
    }

    public float getAverageWeight() {
        return averageWeight;
    }

    public void setAverageWeight(float averageWeight) {
        this.averageWeight = averageWeight;
    }
}
