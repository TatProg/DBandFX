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
import javafx.scene.control.cell.PropertyValueFactory;
import model.Quantity;
import tableView.ViewQuantity;

public class quantityController implements Initializable {

    @FXML
    private TableView<Quantity> table;

    @FXML
    private TableColumn<Quantity, String> c1;

    @FXML
    private TableColumn<Quantity, String> c2;

    @FXML
    void Reload(ActionEvent event) {
        ViewQuantity vq = new ViewQuantity();
        try {
            setTable = vq.TableQuantity();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table.setItems(setTable);
        table.refresh();
    }

    @FXML
    void Upload(ActionEvent event) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("QuantityOUT.txt")));
        setTable.clear();
        reader.lines().forEach(line -> {
            String[] words = line.split(" , ");
            setTable.add(new Quantity(
                    words[0],
                    Integer.parseInt(words[1])
            ));
        });
        table.refresh();
        reader.close();
    }

    @FXML
    void Save(ActionEvent event) throws IOException {
        Quantity.WriteData(setTable, "QuantityIN.txt");
    }

    ObservableList<Quantity> setTable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewQuantity vq = new ViewQuantity();
        try {
            setTable = vq.TableQuantity();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table.setItems(setTable);
    }
}