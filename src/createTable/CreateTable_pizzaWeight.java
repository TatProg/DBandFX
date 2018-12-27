package createTable;

import javafx.collections.ObservableList;
import model.Weight;

import java.sql.*;

import static model.Weight.weightObservableList;

public class CreateTable_pizzaWeight {
    public Statement statement;
    public Connection connection;
    private PreparedStatement preparedStatement;

    private static final String pizzaWeightTRASHToSQL =
            "INSERT INTO pizzaWeightTRASH (pizzaName, weight) VALUES (?, ?)";
    private static final String pizzaWeightToSQL =
            "INSERT INTO pizzaWeight (pizzaName, weight) VALUES (?, ?)";

    public ObservableList<Weight> CreateWeightTable() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        statement.execute("DROP TABLE IF EXISTS pizzaWeight;");
        statement.execute("CREATE TABLE pizzaWeight(" +
                "pizzaName TEXT NOT NULL," +
                "weight INT NOT NULL);");

        statement.execute("DROP TABLE IF EXISTS pizzaWeightTRASH;");
        statement.execute("CREATE TABLE pizzaWeightTRASH(" +
                "pizzaName TEXT NOT NULL," +
                "weight INT NOT NULL);");

        for (Weight weight : weightObservableList) {
            preparedStatement = connection.prepareStatement(pizzaWeightTRASHToSQL);
            preparedStatement.setString(1, weight.getPizzaName());
            preparedStatement.setInt(2, weight.getWeight());
            preparedStatement.execute();
        }

        weightObservableList.clear();

        ResultSet rs = statement.executeQuery("SELECT * FROM pizzaWeightTRASH GROUP BY pizzaName");
        while (rs.next()) {
            String pn = rs.getString("pizzaName");
            int w = rs.getInt("weight");
            weightObservableList.add(new Weight(pn, w));
            preparedStatement = connection.prepareStatement(pizzaWeightToSQL);
            preparedStatement.setString(1, pn);
            preparedStatement.setInt(2, w);
            preparedStatement.execute();
        }

        statement.execute("DROP TABLE pizzaWeightTRASH");

        statement.execute("CREATE TRIGGER checkPizzaWeight\n" +
                "  AFTER INSERT" +
                "  ON pizzaWeight " +
                "BEGIN" +
                "  DELETE" +
                "  FROM pizzaWeight" +
                "  WHERE weight < 0;" +
                "END;");
        return weightObservableList;
    }
}
