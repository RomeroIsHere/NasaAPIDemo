package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import com.example.nasaapidemo.Models.MAPOD.APOD;
import com.example.nasaapidemo.Models.MIVL.Collection;
import com.example.nasaapidemo.Models.MIVL.Data;
import com.example.nasaapidemo.Models.MIVL.Item;
import com.example.nasaapidemo.apicontroller.ImageRetriever;
import com.example.nasaapidemo.apicontroller.NIVLConsumer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class IVLController implements Initializable {
    @FXML
    TextField txtBuscar;

    @FXML
    HBox contenedor;

    List<APOD> a_listAPOD=new ArrayList();

   // APODDao a_APODDao=new APODDao();

    @FXML
    public void m_onClickBuscar() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NIVLConsumer ivl = new NIVLConsumer();
        Item[] arr = ivl.searchbyYearRange("2014","2015");
        /*try{
            for(int i = 0; i < arr.length; i++){

                ImageRetriever img = new ImageRetriever();
                ImageView imageView = new ImageView(img.getFromURL(arr[i].getHref()));
                imageView.setFitWidth(200);
                imageView.setPreserveRatio(true);
                contenedor.getChildren().add(imageView);

            }
        }
        catch (Exception e){

        }*/
        //System.out.println(arr[0].getHref());
    }

    @FXML
    private void onReturn(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main-view.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}

