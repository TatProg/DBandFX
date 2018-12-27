package tableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pizza;

import java.sql.*;

public class ViewPizzaTypes {
    public Statement statement;
    public Connection connection;

    public ObservableList<Pizza> TablePizzaTypes() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        ObservableList<Pizza> pizzaList = FXCollections.observableArrayList();
        ResultSet rs = statement.executeQuery("SELECT * FROM pizzaTypes GROUP BY restaurant;");
        while (rs.next()) {
            String s1 = rs.getString("restaurant");
            String s2 = rs.getString("name");
            pizzaList.add(new Pizza(s1, s2));
        }
        return pizzaList;
    }
}