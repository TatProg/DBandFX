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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Office;
import model.Pizza;
import service.ServiceOfficeAddress;
import tableView.ViewDistrict;
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
    private TextField textField1;

    @FXML
    private TextField textField3;

    @FXML
    private TextField textField2;

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
    void Delete(ActionEvent event) throws SQLException {
        Office newOffice = table.getSelectionModel().getSelectedItem();
        ServiceOfficeAddress soa = new ServiceOfficeAddress();
        soa.DeleteOfficeFromTable(newOffice);

        ViewOfficesAddress voa = new ViewOfficesAddress();
        setTable = voa.TableOfficesAddress();
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("office"));
        c2.setCellValueFactory(new PropertyValueFactory<>("members"));
        table.setItems(setTable);
        table.refresh();

    }

    @FXML
    void Change(ActionEvent event) throws SQLException {
        String s1 = textField1.getText();
        String s2 = textField2.getText();
        String s3 = textField3.getText();
        Office oldOffice = new Office(s1, s2, Integer.parseInt(s3));
        Office newOffice = table.getSelectionModel().getSelectedItem();

        ServiceOfficeAddress soa = new ServiceOfficeAddress();
        soa.DeleteOfficeFromTable(oldOffice);
        soa.AddOfficeToTable(newOffice);

        ViewOfficesAddress voa = new ViewOfficesAddress();

        setTable = voa.TableOfficesAddress();
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("office"));
        c2.setCellValueFactory(new PropertyValueFactory<>("members"));
        table.setItems(setTable);
        table.refresh();
    }

    @FXML
    void addButton(ActionEvent event) throws SQLException {
        String s1 = textField1.getText();
        String s2 = textField2.getText();
        String s3 = textField3.getText();

        Office office = new Office(s1, s2, Integer.parseInt(s3));

        ServiceOfficeAddress service = new ServiceOfficeAddress();
        service.AddOfficeToTable(office);

        ViewOfficesAddress voa = new ViewOfficesAddress();

        try {
            setTable = voa.TableOfficesAddress();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("place"));
        c2.setCellValueFactory(new PropertyValueFactory<>("members"));
        table.setItems(setTable);
        table.refresh();
    }

    @FXML
    void Reload(ActionEvent event) {
        ViewOfficesAddress voa = new ViewOfficesAddress();
        try {
            setTable = voa.TableOfficesAddress();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("place"));
        c2.setCellValueFactory(new PropertyValueFactory<>("members"));
        table.setItems(setTable);
        table.refresh();
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