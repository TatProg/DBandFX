package tableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Office;

import java.sql.*;

public class ViewOfficesAddress {
    public Statement statement;
    public Connection connection;

    public ObservableList<Office> TableOfficesAddress() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        ObservableList<Office> officeList = FXCollections.observableArrayList();
        ResultSet rs = statement.executeQuery("SELECT * FROM officesAddress");
        while (rs.next()) {
            String s1 = rs.getString("restaurant");
            String s2 = rs.getString("place");
            int s3 = rs.getInt("members");
            officeList.add(new Office(s1, s2, s3));
        }
        return officeList;
    }
}
