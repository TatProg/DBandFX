package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.District;
import service.ServiceDistrict;
import tableView.ViewDistrict;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class districtController implements Initializable {

    @FXML
    private TableView<District> table;

    @FXML
    private TableColumn<District, String> c1;

    @FXML
    private TableColumn<District, String> c2;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textField1;

    @FXML
    void addButton(ActionEvent event) throws SQLException {
        String s1 = textField1.getText();
        String s2 = textField2.getText();

        District district = new District(s1, s2);

        ServiceDistrict service = new ServiceDistrict();
        service.AddDistrictToTable(district);

        ViewDistrict vd = new ViewDistrict();

        setTable = vd.TableDistrict();
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("district"));
        table.setItems(setTable);
        table.refresh();
    }

    @FXML
    void Delete(ActionEvent event) throws SQLException {
        District oldDistrict = table.getSelectionModel().getSelectedItem();
        String s1 = textField1.getText();
        String s2 = textField2.getText();

        District newDistrict = new District(s1, s2);

        ServiceDistrict service = new ServiceDistrict();
        service.DeleteDistrictFromTable(oldDistrict);
        service.AddDistrictToTable(newDistrict);

        ViewDistrict vd = new ViewDistrict();

        setTable = vd.TableDistrict();
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("district"));
        table.setItems(setTable);
        table.refresh();
    }

    @FXML
    void Change(ActionEvent event) throws SQLException {
        District oldDistrict = table.getSelectionModel().getSelectedItem();
        String s1 = textField1.getText();
        String s2 = textField2.getText();
        District district = new District(s1, s2);

        ServiceDistrict service = new ServiceDistrict();
        service.DeleteDistrictFromTable(oldDistrict);
        service.AddDistrictToTable(district);

        ViewDistrict vd = new ViewDistrict();

        setTable = vd.TableDistrict();
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("district"));
        table.setItems(setTable);
        table.refresh();
    }

    @FXML
    void Upload(ActionEvent event) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("districtOUT.txt")));
        setTable.clear();
        reader.lines().forEach(line -> {
            String[] words = line.split(" , ");
            setTable.add(new District(
                    words[0],
                    words[1]
            ));
        });
        table.refresh();
        reader.close();
    }

    @FXML
    void Save(ActionEvent event) throws IOException {
        District.WriteData(setTable, "districtIN.txt");
    }

    ObservableList<District> setTable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewDistrict vd = new ViewDistrict();
        try {
            setTable = vd.TableDistrict();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("district"));
        table.setItems(setTable);
    }

}
