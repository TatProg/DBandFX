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
import tableView.ViewOfficesAddress;

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
    void Upload(ActionEvent event) {
        //officeAddressIN.txt
    }

    @FXML
    void Save(ActionEvent event) {
        //officeAddressOUT.txt
    }

    @FXML
    void Delete(ActionEvent event) {

    }

    @FXML
    void Change(ActionEvent event) {

    }

    @FXML
    void addButton(ActionEvent event) {

    }

    ObservableList<Office> setTable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewOfficesAddress voa = new ViewOfficesAddress();
        try {
            setTable = voa.TableOfficesAddress();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("place"));
        c3.setCellValueFactory(new PropertyValueFactory<>("members"));
        table.setItems(setTable);
    }
}