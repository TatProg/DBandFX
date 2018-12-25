package main;

import createTable.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/sample.fxml"));
        primaryStage.setTitle("DataBase");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        CreateTable_pizzaTypes ctpt = new CreateTable_pizzaTypes();
        CreateTable_officesAddress ctoa = new CreateTable_officesAddress();
        CreateTable_defaultTable ctpd = new CreateTable_defaultTable();
        CreateTable_quantityDistrict ctqd = new CreateTable_quantityDistrict();
        CreateTable_averageTable ctat = new CreateTable_averageTable();

        ctpd.CreatePizzaDefaultTable();
        ctpt.CreatePizzaTable();
        ctoa.CreateTableOffice();
        ctqd.CreatePizzaTable();
        ctat.CreateAverageTable();

        launch(args);
    }
}

