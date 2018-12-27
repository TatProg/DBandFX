package tableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Weight;

import java.sql.*;

public class ViewPizzaWeight {
    public Statement statement;
    public Connection connection;

    public ObservableList<Weight> TableWeight() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        ObservableList<Weight> weightList = FXCollections.observableArrayList();
        ResultSet rs = statement.executeQuery("SELECT * FROM pizzaWeight");
        while (rs.next()) {
            String s1 = rs.getString("pizzaName");
            int s3 = rs.getInt("weight");
            weightList.add(new Weight(s1, s3));
        }
        return weightList;

    }
}