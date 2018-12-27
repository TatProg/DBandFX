package main;

import createTable.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.Service;
import tableView.ViewPizzaWeight;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/sample.fxml"));
        primaryStage.setTitle("DataBase");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        CreateTable_defaultTable ctpd = new CreateTable_defaultTable();
        CreateTable_pizzaTypes ctpt = new CreateTable_pizzaTypes();
        CreateTable_officesAddress ctoa = new CreateTable_officesAddress();
        CreateTable_quantity ctq = new CreateTable_quantity();
        CreateTable_district ctd = new CreateTable_district();
        CreateTable_pizzaWeight ctpw = new CreateTable_pizzaWeight();

        ctpd.CreatePizzaDefaultTable();
        ctpt.CreatePizzaTable();
        ctoa.CreateTableOffice();
        ctq.CreateTableQuantity();
        ctd.CreateTableDistrict();
        ctpw.CreateWeightTable();

        launch(args);
    }
}

