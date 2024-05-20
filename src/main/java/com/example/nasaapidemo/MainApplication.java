package com.example.nasaapidemo;

import com.example.nasaapidemo.apicontroller.ImageRetriever;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Log-In");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        ImageRetriever ir=new ImageRetriever();
        try {
            Image hekate=ir.getFromURL("https://images-assets.nasa.gov/image/NHQ201907180120/NHQ201907180120~thumb.jpg");
            System.out.println(hekate.getUrl());
            System.out.println(hekate.getPixelReader().toString());
            System.out.println(hekate.getHeight()+" && "+hekate.getWidth()
            );
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (URISyntaxException e) {
            System.out.println("Wrong URI"+e.getLocalizedMessage());
        }
        launch();
    }
}