package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginController {

    @FXML
    TextField txtUser, txtPassword;

    @FXML
    private void m_onDirigir() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("NASA");
        stage.setScene(scene);
        stage.show();
    }
}
