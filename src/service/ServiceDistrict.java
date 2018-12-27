package service;

import model.District;

import java.sql.*;

public class ServiceDistrict {
    private PreparedStatement preparedStatement;
    public Statement statement;
    public Connection connection;

    public void AddDistrictToTable(District district) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        preparedStatement = connection.prepareStatement(
                "INSERT INTO districtTable (restaurant, district) VALUES (?, ?)");
        preparedStatement.setString(1, district.getRestaurant());
        preparedStatement.setString(2, district.getDistrict());
        preparedStatement.execute();
    }

    public void DeleteDistrictFromTable(District district) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        preparedStatement = connection.prepareStatement(
                "DELETE FROM districtTable WHERE restaurant = ? AND district = ?");
        preparedStatement.setString(1, district.getDistrict());
        preparedStatement.setString(2, district.getDistrict());
        preparedStatement.execute();

    }
}
