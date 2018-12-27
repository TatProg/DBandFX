package controller;

import createTable.CreateTable_district;
import createTable.CreateTable_pizzaTypes;
import createTable.CreateTable_quantity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.District;
import model.Pizza;
import model.Quantity;
import model.Weight;
import service.Service;
import service.ServicePizzaTypes;
import tableView.ViewDistrict;
import tableView.ViewPizzaTypes;
import tableView.ViewPizzaWeight;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class menuController implements Initializable {

    @FXML
    private TableView<Weight> table;

    @FXML
    private TableColumn<Weight, String> c1;

    @FXML
    private TableColumn<Weight, String> c2;

    //Service service;

    @FXML
    void butPrvol(ActionEvent event) throws SQLException {
        ServicePizzaTypes service = new ServicePizzaTypes();
        Weight selectedPizza = table.getSelectionModel().getSelectedItem();
        boolean flag = service.OrderPizzaToDistrict(selectedPizza, "Приволжский");
        System.out.println(flag);
        if (flag) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Доставка Пиццы");
            alert1.setHeaderText("Пицца успешно доставлена");
            alert1.setContentText(selectedPizza.getPizzaName() + " доставлена в Приволжский район");
            alert1.showAndWait();
        } else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Доставка Пиццы");
            alert2.setHeaderText("Выберите другую пиццу или район доставки");
            alert2.setContentText((selectedPizza.getPizzaName() + " невозможно доставить в Приволжский район"));
            alert2.showAndWait();
        }
    }

    @FXML
    void butSov(ActionEvent event) throws SQLException {
        ServicePizzaTypes service = new ServicePizzaTypes();
        Weight selectedPizza = table.getSelectionModel().getSelectedItem();
        boolean flag = service.OrderPizzaToDistrict(selectedPizza, "Советский");
        if (flag) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Доставка Пиццы");
            alert1.setHeaderText("Пицца успешно доставлена");
            alert1.setContentText(selectedPizza.getPizzaName() + " доставлена в Советский район");
            alert1.showAndWait();
        } else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Доставка Пиццы");
            alert2.setHeaderText("Выберите другую пиццу или район доставки");
            alert2.setContentText((selectedPizza.getPizzaName() + " невозможно доставить в Советский район"));
            alert2.showAndWait();
        }
    }

    @FXML
    void butAvia(ActionEvent event) throws SQLException {
        ServicePizzaTypes service = new ServicePizzaTypes();
        Weight selectedPizza = table.getSelectionModel().getSelectedItem();
        boolean flag = service.OrderPizzaToDistrict(selectedPizza, "Авиастроительный");
        if (flag) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Доставка Пиццы");
            alert1.setHeaderText("Пицца успешно доставлена");
            alert1.setContentText(selectedPizza.getPizzaName() + " доставлена в Авиастроительный район");
            alert1.showAndWait();
        } else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Доставка Пиццы");
            alert2.setHeaderText("Выберите другую пиццу или район доставки");
            alert2.setContentText((selectedPizza.getPizzaName() + " невозможно доставить в Авиастроительный район"));
            alert2.showAndWait();
        }
    }

    ObservableList<Weight> setTable = FXCollections.observableArrayList();


    //FixMe - add new table
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewPizzaWeight vpw = new ViewPizzaWeight();
        try {
            setTable = vpw.TableWeight();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("pizzaName"));
        c2.setCellValueFactory(new PropertyValueFactory<>("weight"));
        table.setItems(setTable);
        table.refresh();
    }
}
