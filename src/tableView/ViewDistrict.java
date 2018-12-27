package tableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.District;

import java.sql.*;

public class ViewDistrict {
    public Statement statement;
    public Connection connection;

    public ObservableList<District> TableDistrict() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        ObservableList<District> districtList = FXCollections.observableArrayList();
        ResultSet rs = statement.executeQuery("SELECT * FROM districtTable");
        while (rs.next()) {
            String s1 = rs.getString("restaurant");
            String s3 = rs.getString("district");
            districtList.add(new District(s1, s3));
        }
        return districtList;
    }
}