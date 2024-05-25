package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import com.example.nasaapidemo.apicontroller.APODConsumer;
import com.example.nasaapidemo.apicontroller.MarsConsumer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    TextField txtKey;

    @FXML
    Button btn_apli,btn_APOD,btn_IVL,btn_mars;


    String key = "";

    @FXML
    private void onKeyClick(){
        key = txtKey.getText();
    }

    @FXML
    private void onAPODonClick(javafx.event.ActionEvent actionEvent) throws Exception{
        btn_apli.setDisable(true);
        btn_APOD.setDisable(true);
        btn_IVL.setDisable(true);
        btn_mars.setDisable(true);
        new Thread(()-> {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("APOD-view.fxml"));
            try {
                ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

                Platform.runLater(()->{
                    APODController apodController = fxmlLoader.getController();
                    apodController.setAPIKey(key);
                });




        }).start();



    }

    @FXML
    private void onIVLonClick(javafx.event.ActionEvent actionEvent) throws Exception{


        btn_apli.setDisable(true);
        btn_APOD.setDisable(true);
        btn_IVL.setDisable(true);
        btn_mars.setDisable(true);
        new Thread(()-> {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("IVL-view.fxml"));
            try {
                ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();

    }

    @FXML
    private void onMarsonClick(javafx.event.ActionEvent actionEvent) throws Exception{
        btn_apli.setDisable(true);
        btn_APOD.setDisable(true);
        btn_IVL.setDisable(true);
        btn_mars.setDisable(true);
        new Thread(()-> {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Mars-view.fxml"));


            try {
                ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

               Platform.runLater(()->{
                   MarsController marsController=fxmlLoader.getController();
                   try {
                       marsController.setAPIKey(key);
                   } catch (Exception e) {
                       throw new RuntimeException(e);
                   }
               });



        }).start();

    }


}