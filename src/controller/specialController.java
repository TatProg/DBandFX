package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class specialController {

    @FXML
    void b1(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SELECT");
        alert.setHeaderText("Вывести все районы доставки без повторений.");
        alert.setContentText("SELECT district FROM quantityDistrict GROUP BY district;");
        alert.showAndWait();
    }

    @FXML
    void b2(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SELECT");
        alert.setHeaderText("Вывести все пиццы, которые весят больше 400 г.");
        alert.setContentText("SELECT name, weight FROM pizzaTypes WHERE weight > 400 GROUP BY name;");
        alert.showAndWait();
    }

    @FXML
    void b3(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SELECT");
        alert.setHeaderText("Для каждой пиццерии вывести число районов доставки.");
        alert.setContentText("SELECT restaurant, quantity FROM quantityDistrict GROUP BY restaurant;");
        alert.showAndWait();
    }

    @FXML
    void b4(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SELECT");
        alert.setHeaderText("Для каждой пиццерии вывести среднее число работников по всем офисам и суммарное количество работников.");
        alert.setContentText("SELECT restaurant, SUM(members),AVG(members) FROM officesAddress GROUP BY restaurant;");
        alert.showAndWait();
    }

    @FXML
    void b5(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SELECT");
        alert.setHeaderText("Для каждой пиццерии вывести районы доставки и количество позиций.");
        alert.setContentText("SELECT restaurant, quantity, district FROM quantityDistrict;");
        alert.showAndWait();
    }

    @FXML
    void b6(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SELECT");
        alert.setHeaderText("Для каждой пиццерии вывести суммарное количество работников, количество позиций, средний размер пиццы.");
        alert.setContentText("SELECT averageTable.restaurant,averageTable.averageMembers, quantityDistrict.quantity, averageTable.averageWeight\n" +
                "FROM averageTable, quantityDistrict\n" +
                "WHERE averageTable.restaurant = quantityDistrict.restaurant\n" +
                "GROUP BY averageTable.restaurant;");
        alert.showAndWait();
    }

    @FXML
    void b7(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SELECT");
        alert.setHeaderText("Вывести пиццерии в которых минимальное количество сотрудников в офисе больше, чем среднее количество по всем офисам.");
        alert.setContentText("SELECT * FROM officesAddress WHERE members>(SELECT Avg(members) AS [Avg-members] FROM officesAddress);");
        alert.showAndWait();
    }

    @FXML
    void initialize() {

    }
}
