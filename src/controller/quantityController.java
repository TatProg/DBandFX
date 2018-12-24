package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import createTable.CreateTable_quantityDistrict;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.District;

public class quantityController implements Initializable {

    @FXML
    private TableColumn<District, String> c3;

    @FXML
    private TableView<District> table;

    @FXML
    private TableColumn<District, String> c1;

    @FXML
    private TableColumn<District, String> c2;

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

    ObservableList<District> setTable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CreateTable_quantityDistrict ctqd = new CreateTable_quantityDistrict();
        try {
            setTable = ctqd.CreatePizzaTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        c3.setCellValueFactory(new PropertyValueFactory<>("district"));
        table.setItems(setTable);
    }
}