package controller;

import createTable.CreateTable_quantityDistrict;
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
import tableView.ViewPizzaTypes;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class menuController implements Initializable {

    @FXML
    private TableView<Pizza> table;

    @FXML
    private TableColumn<Pizza, String> c1;

    @FXML
    private TableColumn<Pizza, String> c2;

    @FXML
    void butPrvol(ActionEvent event) throws SQLException {
        Pizza selectedPizza = table.getSelectionModel().getSelectedItem();
        String selectedRestaurant = selectedPizza.getRestaurant();

        CreateTable_quantityDistrict ctqd = new CreateTable_quantityDistrict();
        ObservableList<District> pizzaList = ctqd.CreatePizzaTable();

        boolean flag = false;
        for (District district : pizzaList) {
            if ((district.getRestaurant().equals(selectedRestaurant))
                    && (district.getDistrict().equals("Приволжский"))) {
                flag = true;
            }
        }

        if (flag) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Доставка Пиццы");
            alert1.setHeaderText("Пицца успешно доставлена");
            alert1.setContentText(selectedPizza.getName() + " доставлена в Приволжский район");
            alert1.showAndWait();
        } else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Доставка Пиццы");
            alert2.setHeaderText("Выберите другую пиццу или район доставки");
            alert2.setContentText((selectedPizza.getName() + " невозможно доставить в Приволжский район"));
            alert2.showAndWait();
        }
    }

    @FXML
    void butSov(ActionEvent event) throws SQLException {
        Pizza selectedPizza = table.getSelectionModel().getSelectedItem();
        String selectedRestaurant = selectedPizza.getRestaurant();

        CreateTable_quantityDistrict ctqd = new CreateTable_quantityDistrict();
        ObservableList<District> pizzaList = ctqd.CreatePizzaTable();

        boolean flag = false;
        for (District district : pizzaList) {
            if ((district.getRestaurant().equals(selectedRestaurant))
                    && (district.getDistrict().equals("Советский"))) {
                flag = true;
            }
        }

        if (flag) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Доставка Пиццы");
            alert1.setHeaderText("Пицца успешно доставлена");
            alert1.setContentText(selectedPizza.getName() + " доставлена в Советский район");
            alert1.showAndWait();
        } else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Доставка Пиццы");
            alert2.setHeaderText("Выберите другую пиццу или район доставки");
            alert2.setContentText((selectedPizza.getName() + " невозможно доставить в Советский район"));
            alert2.showAndWait();
        }
    }

    @FXML
    void butAvia(ActionEvent event) throws SQLException {
        Pizza selectedPizza = table.getSelectionModel().getSelectedItem();
        String selectedRestaurant = selectedPizza.getRestaurant();

        CreateTable_quantityDistrict ctqd = new CreateTable_quantityDistrict();
        ObservableList<District> pizzaList = ctqd.CreatePizzaTable();

        boolean flag = false;
        for (District district : pizzaList) {
            if ((district.getRestaurant().equals(selectedRestaurant))
                    && (district.getDistrict().equals("Авиастроительный"))) {
                flag = true;
            }
        }

        if (flag) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Доставка Пиццы");
            alert1.setHeaderText("Пицца успешно доставлена");
            alert1.setContentText(selectedPizza.getName() + " доставлена в Авиастроительный район");
            alert1.showAndWait();
        } else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Доставка Пиццы");
            alert2.setHeaderText("Выберите другую пиццу или район доставки");
            alert2.setContentText((selectedPizza.getName() + " невозможно доставить в Авиастроительный район"));
            alert2.showAndWait();
        }
    }

    ObservableList<Pizza> setTable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewPizzaTypes vpt = new ViewPizzaTypes();
        try {
            setTable = vpt.TablePizzaTypesMenu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("name"));
        c2.setCellValueFactory(new PropertyValueFactory<>("weight"));
        table.setItems(setTable);
        table.refresh();
    }

//    @FXML
//    public void initialize() {
//        ViewPizzaTypes vpt = new ViewPizzaTypes();
//        try {
//            setTable = vpt.TablePizzaTypesMenu();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        c1.setCellValueFactory(new PropertyValueFactory<>("name"));
//        c2.setCellValueFactory(new PropertyValueFactory<>("weight"));
//        table.setItems(setTable);
//    }
}
