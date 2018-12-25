package service;

import createTable.CreateTable_quantityDistrict;
import javafx.collections.ObservableList;
import model.District;
import model.Pizza;

import java.sql.*;

public class Service {
    private PreparedStatement preparedStatement;

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

    public boolean PossibilityOfDelivery(String restaurantPizza, String selectedDistrict) throws SQLException {
        boolean flag = false;
//        CreateTable_quantityDistrict ctqd = new CreateTable_quantityDistrict();
//        ObservableList<District> districtList = ctqd.CreatePizzaTable();
//        String districtRestaurant;
//        String districtDistrict;
//        for (District districtFromList : districtList) {
//            districtRestaurant = districtFromList.getRestaurant();
//            districtDistrict = districtFromList.getDistrict();
//            if ((restaurantPizza.equals(districtRestaurant))
//                    && (selectedDistrict.equals(districtDistrict))) {
//                flag = true;
//            }
//        }
        return flag;
    }
}
