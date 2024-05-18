package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

public class MainController {

    @FXML
    private void onAPODonClick() throws Exception{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("APOD-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("APOD");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onReportonClick() throws Exception{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Reports-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onIVLonClick() throws Exception{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("IVL-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Images and Videos Library");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onMarsonClick() throws Exception{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Mars-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Mars");
        stage.setScene(scene);
        stage.show();
    }

}