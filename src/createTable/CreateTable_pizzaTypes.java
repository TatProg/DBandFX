package createTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pizza;
import model.Weight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateTable_pizzaTypes {
    public Statement statement;
    public Connection connection;
    private PreparedStatement preparedStatement;

    private static final String pizzaTypesToSQL =
            "INSERT INTO pizzaTypes (restaurant, name) VALUES (?, ?)";

    //создание таблицы пицца - вес
    public ObservableList<Pizza> CreatePizzaTable() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        statement.execute("DROP TABLE IF EXISTS pizzaTypes;");
        statement.execute("CREATE TABLE pizzaTypes (" +
                "restaurant TEXT NOT NULL," +
                "name TEXT NOT NULL" +
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
                preparedStatement.execute();
                pizzaList.add(new Pizza(restaurants, pizzaNameToSQL));
                Weight.weightObservableList.add(new Weight(pizzaNameToSQL, pizzaWeightToSQL));
            }
        }
        statement.execute("CREATE TRIGGER checkRestaurant AFTER INSERT ON pizzaTypes BEGIN DELETE FROM pizzaTypes WHERE restaurant <> 'Папа Джонс' AND restaurant <> 'Додо пицца'  AND restaurant <> 'Домино пицца' AND restaurant <> 'А Рома'; END;");
        return pizzaList;
    }
}
