package createTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.District;
import model.Quantity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateTable_quantity {
    public Statement statement;
    public Connection connection;
    private PreparedStatement preparedStatement;
    private static final String quantityToSQL =
            "INSERT INTO quantityTable (restaurant, quantity) VALUES (?, ?)";

    //создание таблицы пицца - вес
    public ObservableList<Quantity> CreateTableQuantity() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        statement.execute("DROP TABLE IF EXISTS quantityTable;");
        statement.execute("CREATE TABLE quantityTable (" +
                "restaurant TEXT NOT NULL," +
                "quantity INT NOT NULL" +
                ");");

        ObservableList<Quantity> quantityList = FXCollections.observableArrayList();

        ResultSet rs = statement.executeQuery("SELECT * FROM defaultTable;");

        while (rs.next()) {
            String restaurant = rs.getString("restaurant");
            int quantity = rs.getInt("quantity");
            preparedStatement = connection.prepareStatement(quantityToSQL);
            preparedStatement.setString(1, restaurant);
            preparedStatement.setInt(2, quantity);
            preparedStatement.execute();
            quantityList.add(new Quantity(restaurant, quantity));
        }
        return quantityList;
    }
}
