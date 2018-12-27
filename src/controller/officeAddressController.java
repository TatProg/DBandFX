package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
import model.Pizza;
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
    void Upload(ActionEvent event) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("officeAddressIN.txt")));
        setTable.clear();
        reader.lines().forEach(line -> {
            String[] words = line.split(" , ");
            setTable.add(new Office(
                    words[0],
                    words[1],
                    Integer.parseInt(words[2])
            ));
        });
        table.refresh();
        reader.close();
    }

    @FXML
    void Save(ActionEvent event) throws IOException {
        Office.WriteData(setTable, "officeAddressOUT.txt");
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