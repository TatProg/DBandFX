package createTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Average;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateTable_averageTable {
    public Statement statement;
    public Connection connection;
    private PreparedStatement preparedStatement;

    private final String averageMembersToSQL = "INSERT INTO averageTable (restaurant, averageMembers) VALUES (?, ?)";
    private final String averageWeightToSQL = "UPDATE averageTable SET averageWeight = ? WHERE restaurant = ?";

    public ObservableList<Average> CreateAverageTable() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        statement.execute("DROP TABLE IF EXISTS averageTable");
        statement.execute("CREATE TABLE averageTable (" +
                "  restaurant TEXT,\n" +
                "  averageMembers FLOAT,\n" +
                "  averageWeight FLOAT)");

        ObservableList<Average> averageList = FXCollections.observableArrayList();
        ObservableList<Integer> memberList = FXCollections.observableArrayList();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM defaultTable GROUP By restaurant;");
//            ResultSet resultSetForWeight = statement.executeQuery(
//                    "SELECT restaurant, AVG(weight) FROM pizzaTypes GROUP BY restaurant;");
            ResultSet resultSetForMembers = statement.executeQuery(
                    "SELECT restaurant, SUM(members) FROM officesAddress GROUP BY restaurant;");
            while (rs.next()) {
                String restaurant = rs.getString("restaurant");
                int members = resultSetForMembers.getInt(2);
                preparedStatement = connection.prepareStatement(averageMembersToSQL);
                preparedStatement.setString(1, restaurant);
                preparedStatement.setInt(2, members);
                preparedStatement.execute();
                memberList.add(members);
            }
        } catch (SQLException sql) {
            System.out.println("Some problems with SQL");
        }

        int index = 0;

        ResultSet rs = statement.executeQuery("SELECT * FROM defaultTable GROUP By restaurant;");
        ResultSet resultSetForWeight = statement.executeQuery(
                "SELECT restaurant, AVG(weight) FROM pizzaTypes GROUP BY restaurant;");
        while (rs.next()) {
            String restaurant = rs.getString("restaurant");
            float weight = resultSetForWeight.getFloat(2);
            preparedStatement = connection.prepareStatement(averageWeightToSQL);
            preparedStatement.setString(2, restaurant);
            preparedStatement.setFloat(1, weight);
            preparedStatement.execute();
            averageList.add(new Average(restaurant, memberList.get(index), weight));
            index++;
        }
        return averageList;
    }
}
