package tableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.District;
import model.Quantity;

import java.sql.*;

public class ViewQuantity {
    public Statement statement;
    public Connection connection;

    public ObservableList<Quantity> TableQuantity() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        ObservableList<Quantity> districtList = FXCollections.observableArrayList();
        ResultSet rs = statement.executeQuery("SELECT * FROM quantityTable GROUP BY restaurant");
        while (rs.next()) {
            String s1 = rs.getString("restaurant");
            int s3 = rs.getInt("quantity");
            districtList.add(new Quantity(s1, s3));
        }
        return districtList;
    }
}
