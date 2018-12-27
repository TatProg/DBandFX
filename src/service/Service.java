package service;

import javafx.collections.ObservableList;
import model.District;
import model.Pizza;
import model.Weight;
import tableView.ViewDistrict;
import tableView.ViewPizzaTypes;
import tableView.ViewPizzaWeight;

import java.sql.*;
import java.util.ArrayList;

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
            "SELECT restaurant, COUNT(district) FROM quantityDistrict GROUP BY restaurant;";

    //4 task - Для каждой пиццерии вывести среднее число работников по всем офисам и суммарное количество работников.
    private static final String membersInRestaurantsToSQL =
            "SELECT restaurant, SUM(members),AVG(members) FROM officesAddress GROUP BY restaurant;";

    //5 task - Для каждой пиццерии вывести районы доставки и количество позиций.
    private static final String districtsQuantityToSQL =
            "SELECT districtTable.restaurant, districtTable.district, quantityTable.quantity\n" +
                    "FROM districtTable, quantityTable\n" +
                    "WHERE districtTable.restaurant = quantityTable.restaurant;";

    //6 task - Для каждой пиццерии вывести суммарное количество работников, количество позиций, средний размер пиццы
    private static final String SQL = "";

    //7 task - Вывести пиццерии в которых минимальное количество сотрудников в офисе больше, чем среднее количество по всем офисам.
    private static final String minimalMembersToSQL =
            "SELECT * FROM officesAddress WHERE members>(SELECT Avg(members) AS [Avg-members] FROM officesAddress);";

    public Statement statement;
    public Connection connection;

    public void AddPizzaToTable(Pizza pizza, Weight weight) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        preparedStatement = connection.prepareStatement(
                "INSERT INTO pizzaTypes (restaurant, name) VALUES (?, ?)");

        preparedStatement.setString(1, pizza.getRestaurant());
        preparedStatement.setString(2, pizza.getName());
        preparedStatement.execute();
        preparedStatement = connection.prepareStatement(
                "INSERT INTO pizzaWeight (pizzaName, weight) VALUES (?, ?)");

        preparedStatement.setString(1, weight.getPizzaName());
        preparedStatement.setInt(2, weight.getWeight());
        preparedStatement.execute();
    }

    public void DeletePizzaFromTable(Pizza pizza) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        preparedStatement = connection.prepareStatement(
                "DELETE FROM pizzaTypes WHERE restaurant = ? AND name = ?");
        preparedStatement.setString(1, pizza.getName());
        preparedStatement.setString(2, pizza.getRestaurant());
        preparedStatement.execute();

        ViewPizzaWeight vpw = new ViewPizzaWeight();
        for (Weight weight : vpw.TableWeight()) {
            if (pizza.getName().equals(weight.getPizzaName())) {
                preparedStatement = connection.prepareStatement(
                        "DELETE FROM pizzaWeight WHERE pizzaName = ? AND weight = ?");
                preparedStatement.setString(1, pizza.getName());
                preparedStatement.setInt(2, weight.getWeight());
                preparedStatement.execute();
            }
        }
    }

    public boolean OrderPizzaToDistrict(Weight selectedPizza, String districtString) throws SQLException {
        boolean flag = false;

        //Берем Пиццу, затем вытаскимваем название и ищем совпадение с теми ресторанами, где она подается
        ViewPizzaTypes vpt = new ViewPizzaTypes();
        ArrayList<String> restaurantList = new ArrayList<>();
        for (Pizza pizza : vpt.TablePizzaTypes()) {
            if (selectedPizza.getPizzaName().equals(pizza.getName())) {
//                System.out.println(pizza.getName() + " " + pizza.getRestaurant());
                restaurantList.add(pizza.getRestaurant());  //Добавляем этот рест в список
            }
        }

        //Подбираем и добавляем в список те районы, в которых есть рстораны из списка
        ViewDistrict vd = new ViewDistrict();
        ArrayList<String> districtList = new ArrayList<>();
        for (String restaurant : restaurantList) {
            for (District district : vd.TableDistrict()) {
                if (restaurant.equals(district.getRestaurant())) {
                    districtList.add(district.getDistrict());
//                    System.out.println(district.getRestaurant()
//                            + " " + district.getDistrict()
//                            + " " + districtString);
                }
            }
        }

        for (String s : districtList) {
            if (s.equals(districtString)) {
                flag = true;
            }
        }

        return flag;
    }

}
