package model;

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
}
