package createTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.District;
import model.Pizza;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateTable_pizzaTypes {
    private PreparedStatement preparedStatement;
    private static final String pizzaTypesToSQL =
            "INSERT INTO pizzaTypes (restaurant, name, weight) VALUES (?, ?, ?)";
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

    //создание таблицы пицца - вес
    public ObservableList<Pizza> CreatePizzaTable() throws SQLException {
        statement.execute("DROP TABLE IF EXISTS pizzaTypes;");
        statement.execute("CREATE TABLE pizzaTypes (" +
                "restaurant TEXT NOT NULL," +
                "name TEXT NOT NULL," +
                "weight INT NOT NULL" +
                ");");
        ObservableList<Pizza> pizzaList = FXCollections.observableArrayList();

        ResultSet rs = statement.executeQuery("SELECT * FROM defaultTable;");

        while (rs.next()) {
            List<String> list = new ArrayList<String>();
            String restaurants = rs.getString("restaurant");
            String pizzaName = rs.getString("pizzaName");
            pizzaName = pizzaName.replaceAll("\\{", "");
            pizzaName = pizzaName.replaceAll("\\}", "");
            pizzaName = pizzaName.substring(1);
            pizzaName = pizzaName.substring(0, pizzaName.length() - 1);
            int kolvoPizza = 100;
            for (String s : pizzaName.split("], \\[", kolvoPizza)) {
                list.add(s);
            }
            for (String s : list) {
                String pizzaNameToSQL = s.substring(0, s.indexOf(","));
                String pizzaWeightToString = s.substring(s.indexOf(", ") + 2);
                int pizzaWeightToSQL = Integer.parseInt(pizzaWeightToString);
                preparedStatement = connection.prepareStatement(pizzaTypesToSQL);
                preparedStatement.setString(1, restaurants);
                preparedStatement.setString(2, pizzaNameToSQL);
                preparedStatement.setInt(3, pizzaWeightToSQL);
                preparedStatement.execute();
                pizzaList.add(new Pizza(restaurants, pizzaNameToSQL, pizzaWeightToSQL));
            }

        }
        statement.close();
        connection.close();
        return pizzaList;
    }
}
