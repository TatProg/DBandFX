package service;

import javafx.collections.ObservableList;
import model.District;
import model.Pizza;
import model.Quantity;
import model.Weight;
import tableView.ViewDistrict;
import tableView.ViewPizzaTypes;
import tableView.ViewPizzaWeight;
import tableView.ViewQuantity;

import java.sql.*;
import java.util.ArrayList;

public class ServicePizzaTypes {
    private PreparedStatement preparedStatement;
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

    public void AddToQuantity(Pizza pizza) throws SQLException {
        ViewQuantity vq = new ViewQuantity();
        ObservableList<Quantity> quantityList = vq.TableQuantity();
        for (Quantity quantity : quantityList) {
            if (quantity.getRestaurant().equals(pizza.getRestaurant())) {
                preparedStatement = connection.prepareStatement("UPDATE quantityTable " +
                        "SET quantity=quantity+1 WHERE restaurant = ?;");
                preparedStatement.setString(1, pizza.getRestaurant());
                preparedStatement.execute();
            }
        }
    }

    public void DeleteFromQuantity(Pizza pizza) throws SQLException {
        ViewQuantity vq = new ViewQuantity();
        ObservableList<Quantity> quantityList = vq.TableQuantity();
        for (Quantity quantity : quantityList) {
            if (quantity.getRestaurant().equals(pizza.getRestaurant())) {
                preparedStatement = connection.prepareStatement("UPDATE quantityTable " +
                        "SET quantity=quantity-1 WHERE restaurant = ?;");
                preparedStatement.setString(1, pizza.getRestaurant());
                preparedStatement.execute();
            }
        }
    }
}
