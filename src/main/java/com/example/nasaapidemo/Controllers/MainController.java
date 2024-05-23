package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

public class MainController {

    @FXML
    TextField txtKey;

    String key = "";

    @FXML
    private void onKeyClick(){
        key = txtKey.getText();
    }

    @FXML
    private void onAPODonClick(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("APOD-view.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        APODController apod = fxmlLoader.getController();
        apod.setKey(key);
    }

    @FXML
    private void onIVLonClick(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("IVL-view.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        //IVLController ivl = fxmlLoader.getController();
        //ivl.setKey(key);
    }

    @FXML
    private void onMarsonClick(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Mars-view.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        MarsController mars = fxmlLoader.getController();
        mars.setKey(key);

    }

}