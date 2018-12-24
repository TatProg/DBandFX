package createTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Office;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateTable_officesAddress {
    private PreparedStatement preparedStatement;
    private static final String officeToSQL =
            "INSERT INTO officesAddress (restaurant, place, members) VALUES (?,?,?)";

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

    //создание таблицы офис - количество работников
    public ObservableList<Office> CreateTableOffice() throws SQLException {
        statement.execute("DROP TABLE IF EXISTS officesAddress;");
        statement.execute("CREATE TABLE officesAddress (" +
                "restaurant TEXT NOT NULL," +
                "place TEXT NOT NULL," +
                "members INT NOT NULL)");

        ObservableList<Office> officeList = FXCollections.observableArrayList();
        ResultSet rs = statement.executeQuery("SELECT * FROM defaultTable ");

        while (rs.next()) {
            List<String> list = new ArrayList<String>();
            String restaurants = rs.getString("restaurant");
            String offices = rs.getString("offices");
            offices = offices.replaceAll("\\{", "");
            offices = offices.replaceAll("\\}", "");
            offices = offices.substring(1);
            for (String s : offices.split(", \"")) {
                list.add(s);
            }
            for (String s : list) {
                String addressToSQL = s.substring(0, s.indexOf("\""));
                String memberToString = s.substring(s.indexOf(":"));
                int memberToSQL = Integer.parseInt(memberToString.substring(2));
                preparedStatement = connection.prepareStatement(officeToSQL);
                preparedStatement.setString(1, restaurants);
                preparedStatement.setString(2, addressToSQL);
                preparedStatement.setInt(3, memberToSQL);
                preparedStatement.execute();
                officeList.add(new Office(restaurants, addressToSQL, memberToSQL));
            }
        }
        statement.close();
        connection.close();
        return officeList;
    }
}
