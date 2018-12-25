package createTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pizza;

import java.sql.*;

public class CreateTable_menu {
    private PreparedStatement preparedStatement;

    public Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement statement;

    {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Pizza> CreateMenuTable() throws SQLException {
        ObservableList<Pizza> pizzaList = FXCollections.observableArrayList();

        ResultSet rs = statement.executeQuery("SELECT restaurant, name, weight FROM pizzaTypes GROUP BY name;");

        while (rs.next()) {
            String restaurant = rs.getString("restaurant");
            String name = rs.getString("name");
            int weight = rs.getInt("weight");
            pizzaList.add(new Pizza(restaurant, name, weight));
        }
//        statement.close();
//        connection.close();
        return pizzaList;
    }
}
