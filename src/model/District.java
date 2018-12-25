package model;

import createTable.CreateTable_quantityDistrict;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class District {
    private String restaurant;
    private int quantity;
    private String district;

    public District(String restaurant, int quantity, String district) {
        this.restaurant = restaurant;
        this.quantity = quantity;
        this.district = district;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public ObservableList<District> selectAllQuantityAndDistricts() throws SQLException {
        CreateTable_quantityDistrict table = new CreateTable_quantityDistrict();
        ObservableList<District> data = FXCollections.observableArrayList();
        data.addAll(table.CreatePizzaTable());
        return data;
    }
}
