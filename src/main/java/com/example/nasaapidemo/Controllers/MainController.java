package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

public class MainController {

    @FXML
    private void onAPODonClick(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("APOD-view.fxml"));
        Parent newView = fxmlLoader.load();
        Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        Scene newScene = new Scene(newView);
        newScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        currentStage.setScene(newScene);
        currentStage.setMaximized(true);
        currentStage.show();
    }

    @FXML
    private void onIVLonClick(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("IVL-view.fxml"));
        Parent newView = fxmlLoader.load();
        Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        Scene newScene = new Scene(newView);
        newScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        currentStage.setScene(newScene);
        currentStage.setMaximized(true);
        currentStage.show();
    }

    @FXML
    private void onMarsonClick(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Mars-view.fxml"));
        Parent newView = fxmlLoader.load();
        Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        Scene newScene = new Scene(newView);
        newScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        currentStage.setScene(newScene);
        currentStage.setMaximized(true);
        currentStage.show();
    }

}