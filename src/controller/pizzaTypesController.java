package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Pizza;
import service.Service;
import tableView.ViewPizzaTypes;

public class pizzaTypesController implements Initializable {

    @FXML
    private TableView<Pizza> table;

    @FXML
    private TableColumn<Pizza, String> c1;

    @FXML
    private TableColumn<Pizza, String> c2;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textField3;

    @FXML
    void addButton(ActionEvent event) throws SQLException {
        String s1 = textField1.getText();
        String s2 = textField2.getText();
        String s3 = textField3.getText();
        Pizza pizza = new Pizza(s1, s2, Integer.parseInt(s3));
        Service service = new Service();
        service.AddPizzaToTable(pizza);

        ViewPizzaTypes vpt = new ViewPizzaTypes();
        try {
            setTable = vpt.TablePizzaTypes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setItems(setTable);
        table.refresh();

//        menuController mc = new menuController();
//        mc.initialize();
    }

    ObservableList<Pizza> setTable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewPizzaTypes vpt = new ViewPizzaTypes();
        try {
            setTable = vpt.TablePizzaTypes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setItems(setTable);
    }
}