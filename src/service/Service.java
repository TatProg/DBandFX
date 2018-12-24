package service;

import java.sql.*;

public class Service {
    private PreparedStatement preparedStatement;

    //6. Для каждой пиццерии вывести суммарное количество работников, количество позиций, средний размер пиццы.

    //1 task - Вывести все районы доставки без повторений.
    private static final String restaurantDistrictsToSQL =
            "SELECT district FROM quantityDistrict GROUP BY district;";

    //2 task - Вывести все пиццы, которые весят больше 400 г.
    private static final String viewPizzaMoreThanToSQL =
            "Select name, weight FROM pizzaTypes WHERE weight > 400 GROUP BY name;";

    //3 task - Для каждой пиццерии вывести число районов доставки.
    private static final String restaurantQuantityToSQL =
            "SELECT restaurant, quantity FROM quantityDistrict GROUP BY restaurant;";

    //4 task - Для каждой пиццерии вывести среднее число работников по всем офисам и суммарное количество работников.
    private static final String membersInRestaurantsToSQL =
            "SELECT restaurant, SUM(members),AVG(members) FROM officesAddress GROUP BY restaurant;";

    //5 task - Для каждой пиццерии вывести районы доставки и количество позиций.
    private static final String districtsQuantityToSQL =
            "SELECT restaurant, quantity, district FROM quantityDistrict;";

    //6 task - Для каждой пиццерии вывести суммарное количество работников, количество позиций, средний размер пиццы
    private static final String SQL =
            "SELECT averageTable.restaurant,averageTable.averageMembers, quantityDistrict.quantity, averageTable.averageWeight\n" +
                    "FROM averageTable, quantityDistrict\n" +
                    "WHERE averageTable.restaurant = quantityDistrict.restaurant\n" +
                    "GROUP BY averageTable.restaurant;";

    //7 task - Вывести пиццерии в которых минимальное количество сотрудников в офисе больше, чем среднее количество по всем офисам.
    private static final String minimalMembersToSQL =
            "SELECT * FROM officesAddress WHERE members>(SELECT Avg(members) AS [Avg-members] FROM officesAddress);";

    private static final String triggerUpdate =
            "";

    public Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/databasesemestr/src/main/resources/pizzaDataBase");
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


}
