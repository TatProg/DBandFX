package controller;

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
import model.Average;
import tableView.ViewAverage;

public class averageController implements Initializable {

    @FXML
    private TableColumn<Average, String> c3;

    @FXML
    private TableView<Average> table;

    @FXML
    private TableColumn<Average, String> c1;

    @FXML
    private TableColumn<Average, String> c2;

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

    ObservableList<Average> setTable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewAverage va = new ViewAverage();
        try {
            setTable = va.TableAverage();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        c2.setCellValueFactory(new PropertyValueFactory<>("averageMembers"));
        c3.setCellValueFactory(new PropertyValueFactory<>("averageWeight"));
        table.setItems(setTable);
    }
}
