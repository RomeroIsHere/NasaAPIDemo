package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import com.example.nasaapidemo.Models.MAPOD.APOD;
import com.example.nasaapidemo.Models.MIVL.Item;
import com.example.nasaapidemo.apicontroller.ImageRetriever;
import com.example.nasaapidemo.apicontroller.NIVLConsumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class IVLController implements Initializable {
    @FXML
    TextField txtBuscar;

    @FXML
    ScrollPane scrollPane;

    @FXML
    private GridPane contenedor;

    List<APOD> a_listAPOD=new ArrayList();

    @FXML
    public void m_onClickBuscar() {
        contenedor.getChildren().clear();
        NIVLConsumer ivl = new NIVLConsumer();
        Item[] arr = ivl.searchByDesc(txtBuscar.getText());
        try {
            int column = 0;
            int row = 0;
            for (Item item : arr) {
                VBox imageBox = new VBox();
                ImageRetriever img = new ImageRetriever();
                ImageView imageView = new ImageView(img.getFromURL(item.getHref()));
                imageView.setFitWidth(200);
                imageView.setPreserveRatio(true);
                imageBox.getChildren().add(imageView);

                contenedor.add(imageBox, column, row);

                column++;
                if (column == 10) {
                    column = 0;
                    row++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NIVLConsumer ivl = new NIVLConsumer();
        Item[] arr = ivl.searchbyYearRange("2020", "2024");
        try {
            int column = 0;
            int row = 0;
            for (Item item : arr) {
                VBox imageBox = new VBox();
                ImageRetriever img = new ImageRetriever();
                ImageView imageView = new ImageView(img.getFromURL(item.getHref()));
                imageView.setFitWidth(200);
                imageView.setPreserveRatio(true);
                imageBox.getChildren().add(imageView);

                contenedor.add(imageBox, column, row);

                column++;
                if (column == 10) {
                    column = 0;
                    row++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void onReturn(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main-view.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}

