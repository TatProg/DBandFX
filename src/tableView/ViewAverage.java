package tableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Average;

import java.sql.*;

public class ViewAverage {
    public Statement statement;
    public Connection connection;

    public ObservableList<Average> TableAverage() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        ObservableList<Average> averageList = FXCollections.observableArrayList();
        ResultSet rs = statement.executeQuery("SELECT * FROM averageTable");
        while (rs.next()) {
            String s1 = rs.getString("restaurant");
            int s2 = rs.getInt("averageMembers");
            float s3 = rs.getFloat("averageWeight");
            averageList.add(new Average(s1, s2, s3));
        }
        return averageList;
    }
}
