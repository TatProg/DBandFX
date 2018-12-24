package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

public class sampleController {

    @FXML
    private Tab tab5;

    @FXML
    private Tab tab3;

    @FXML
    private Tab tab4;

    @FXML
    private Tab tab1;

    @FXML
    private Tab tab2;

    @FXML
    void initialize() {
        FXMLLoader loadTab1 = new FXMLLoader();
        loadTab1.setLocation(getClass().getResource("/view/tab1.fxml"));
        try {
            tab1.setContent(loadTab1.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        FXMLLoader loadTab2 = new FXMLLoader();
        loadTab2.setLocation(getClass().getResource("/view/tab2.fxml"));
        try {
            tab2.setContent(loadTab2.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        FXMLLoader loadTab3 = new FXMLLoader();
        loadTab3.setLocation(getClass().getResource("/view/tab3.fxml"));
        try {
            tab3.setContent(loadTab3.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        FXMLLoader loadTab4 = new FXMLLoader();
        loadTab4.setLocation(getClass().getResource("/view/tab4.fxml"));
        try {
            tab4.setContent(loadTab4.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        FXMLLoader loadTab5 = new FXMLLoader();
        loadTab5.setLocation(getClass().getResource("/view/tab5.fxml"));
        try {
            tab5.setContent(loadTab5.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
