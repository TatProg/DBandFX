package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import createTable.CreateTable_pizzaTypes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Pizza;

public class pizzaTypesController implements Initializable {

    @FXML
    private TableColumn<Pizza, String> c3;

    @FXML
    private TableView<Pizza> table;

    @FXML
    private TableColumn<Pizza, String> c1;

    @FXML
    private TableColumn<Pizza, String> c2;

    @FXML
    void saveClick(ActionEvent event) {

    }

    @FXML
    void loadClick(ActionEvent event) {

    }

    @FXML
    void addClick(ActionEvent event) {

    }

    @FXML
    void editClick(ActionEvent event) {

    }

    @FXML
    void deleteClick(ActionEvent event) {

    }

    ObservableList<Pizza> setTable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CreateTable_pizzaTypes ctpt = new CreateTable_pizzaTypes();
        try {
            setTable = ctpt.CreatePizzaTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        c3.setCellValueFactory(new PropertyValueFactory<>("weight"));
        table.setItems(setTable);
    }
}