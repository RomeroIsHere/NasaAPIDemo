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

import java.awt.event.ActionEvent;
import java.io.IOException;


public class LoginController {

    @FXML
    TextField txtUser, txtPassword;


    public void m_onDirigir(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main-view.fxml"));
        Parent newView = fxmlLoader.load();
        Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        Scene newScene = new Scene(newView);
        newScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        currentStage.setScene(newScene);
        currentStage.centerOnScreen();
        Platform.runLater(() -> {
            currentStage.setMaximized(false);
            currentStage.setMaximized(true);
        });
        currentStage.show();
    }

    public void onRegist(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Register-view.fxml"));
        Parent newView = fxmlLoader.load();
        Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        Scene newScene = new Scene(newView);
        newScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        currentStage.setScene(newScene);
        currentStage.centerOnScreen();
        currentStage.setMaximized(true);
        currentStage.show();
    }
}
