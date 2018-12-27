package service;

import model.Office;

import java.sql.*;

public class ServiceOfficeAddress {
    private PreparedStatement preparedStatement;
    public Statement statement;
    public Connection connection;

    public void AddOfficeToTable(Office office) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        preparedStatement = connection.prepareStatement(
                "INSERT INTO officesAddress (restaurant, place, members) VALUES (?, ?, ?)");

        preparedStatement.setString(1, office.getRestaurant());
        preparedStatement.setString(2, office.getPlace());
        preparedStatement.setInt(3, office.getMembers());
        preparedStatement.execute();
    }

    public void DeleteOfficeFromTable(Office office) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        preparedStatement = connection.prepareStatement(
                "DELETE FROM officesAddress WHERE restaurant = ? AND place = ? AND members = ?");
        preparedStatement.setString(1, office.getRestaurant());
        preparedStatement.setString(2, office.getPlace());
        preparedStatement.setInt(3, office.getMembers());
        preparedStatement.execute();
    }
}
