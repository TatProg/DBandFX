package createTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.District;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateTable_district {
    public Statement statement;
    public Connection connection;
    private PreparedStatement preparedStatement;
    private static final String quantityDistrictsToSQL =
            "INSERT INTO districtTable (restaurant, district) VALUES (?, ?)";

    //создание таблицы пицца - вес
    public ObservableList<District> CreateTableDistrict() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        statement.execute("DROP TABLE IF EXISTS districtTable;");
        statement.execute("CREATE TABLE districtTable (" +
                "restaurant TEXT NOT NULL," +
                "district TEXT NOT NULL" +
                ");");

        ObservableList<District> districtList = FXCollections.observableArrayList();

        ResultSet rs = statement.executeQuery("SELECT * FROM defaultTable;");

        while (rs.next()) {
            List<String> list = new ArrayList<String>();
            String restaurant = rs.getString("restaurant");
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
                preparedStatement.setString(2, s);
                preparedStatement.execute();
                districtList.add(new District(restaurant, s));
            }
        }
        return districtList;
    }
}
