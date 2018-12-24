package createTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.District;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateTable_quantityDistrict {
    private PreparedStatement preparedStatement;
    private static final String quantityDistrictsToSQL =
            "INSERT INTO quantityDistrict (restaurant, quantity, district) VALUES (?, ?, ?)";
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
    public ObservableList<District> CreatePizzaTable() throws SQLException {
        statement.execute("DROP TABLE IF EXISTS quantityDistrict;");
        statement.execute("CREATE TABLE quantityDistrict (" +
                "restaurant TEXT NOT NULL," +
                "quantity INT NOT NULL," +
                "district TEXT NOT NULL" +
                ");");

        ObservableList<District> districtList = FXCollections.observableArrayList();

        ResultSet rs = statement.executeQuery("SELECT * FROM defaultTable;");

        while (rs.next()) {
            List<String> list = new ArrayList<String>();
            String restaurant = rs.getString("restaurant");
            String quantity = rs.getString("quantity");
            String districts = rs.getString("districts");
            districts = districts.replaceAll("\\{", "");
            districts = districts.replaceAll("\\}", "");
            int n = 100;
            for (String s : districts.split(", ", n)) {
                list.add(s);
            }
            for (String s : list) {
                preparedStatement = connection.prepareStatement(quantityDistrictsToSQL);
                preparedStatement.setString(1, restaurant);
                preparedStatement.setString(2, quantity);
                preparedStatement.setString(3, s);
                preparedStatement.execute();
                districtList.add(new District(restaurant, Integer.parseInt(quantity), s));
            }
        }

        statement.close();
        connection.close();
        return districtList;
    }
}
