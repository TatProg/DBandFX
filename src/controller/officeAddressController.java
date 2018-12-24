package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import createTable.CreateTable_officesAddress;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Office;

public class officeAddressController implements Initializable {

    @FXML
    private TableColumn<Office, String> c3;

    @FXML
    private TableView<Office> table;

    @FXML
    private TableColumn<Office, String> c1;

    @FXML
    private TableColumn<Office, String> c2;

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

    ObservableList<Office> setTable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CreateTable_officesAddress ctoa = new CreateTable_officesAddress();
        try {
            setTable = ctoa.CreateTableOffice();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("place"));
        c3.setCellValueFactory(new PropertyValueFactory<>("members"));
        table.setItems(setTable);
    }
}