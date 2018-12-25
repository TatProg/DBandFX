package model;

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
}
