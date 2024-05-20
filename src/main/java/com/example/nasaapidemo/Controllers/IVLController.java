package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import com.example.nasaapidemo.Models.MAPOD.APOD;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class IVLController implements Initializable {
    @FXML
    TextField txtBuscar;

    List<APOD> a_listAPOD=new ArrayList();

   // APODDao a_APODDao=new APODDao();

    @FXML
    public void m_onClickBuscar() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void onReturn(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main-view.fxml"));
        Parent newView = fxmlLoader.load();
        Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        Scene newScene = new Scene(newView);
        newScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        currentStage.setScene(newScene);
        currentStage.setMaximized(true);
        currentStage.show();
    }
}

