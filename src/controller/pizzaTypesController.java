package controller;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import model.Weight;
import service.Service;
import service.ServicePizzaTypes;
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
        Pizza pizza = new Pizza(s1, s2);
        Weight weight = new Weight(s2, Integer.parseInt(s3));

        ServicePizzaTypes service = new ServicePizzaTypes();
        service.AddPizzaToTable(pizza, weight);
        service.AddToQuantity(pizza);

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
    }

    @FXML
    void Change(ActionEvent event) throws SQLException {
        Pizza oldPizza = table.getSelectionModel().getSelectedItem();
        String s1 = textField1.getText();
        String s2 = textField2.getText();
        String s3 = textField3.getText();
        Pizza newPizza = new Pizza(s1, s2);
        Weight newWeight = new Weight(s2, Integer.parseInt(s3));

        ServicePizzaTypes service = new ServicePizzaTypes();

        service.DeletePizzaFromTable(oldPizza);
        service.AddPizzaToTable(newPizza, newWeight);

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
    }

    @FXML
    void Delete(ActionEvent event) throws SQLException {
        Pizza oldPizza = table.getSelectionModel().getSelectedItem();
        ServicePizzaTypes service = new ServicePizzaTypes();
        service.DeletePizzaFromTable(oldPizza);

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
    }

    @FXML
    void Save(ActionEvent event) throws IOException {
        Pizza.WriteData(setTable, "PizzaTypesIN.txt");
    }

    @FXML
    void Upload(ActionEvent event) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("PizzaTypesOUT.txt")));
        setTable.clear();
        reader.lines().forEach(line -> {
            String[] words = line.split(" , ");
            setTable.add(new Pizza(
                    words[0],
                    words[1]
            ));
        });
        table.refresh();
        reader.close();
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