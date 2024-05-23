package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import com.example.nasaapidemo.Models.MIVL.Item;
import com.example.nasaapidemo.Models.MMars.Photos;
import com.example.nasaapidemo.Models.MMars.Rover;
import com.example.nasaapidemo.apicontroller.ImageRetriever;
import com.example.nasaapidemo.apicontroller.MarsConsumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MarsController implements Initializable {

    @FXML
    private ComboBox<String> cmbNames;

    @FXML
    private HBox contenedor;

    @FXML
    private Label name, landingDate, launchDate, status, maxSol, maxDate, totalPhotos;

    @FXML
    private GridPane images;

    String key="";

    MarsConsumer marsConsumer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        marsConsumer=new MarsConsumer("fk5hNwja1lbzQEY3QbMoRpuDoqGnUgmhVYT9V1ou");
        if (key != ""){
            marsConsumer=new MarsConsumer(key);
        }

        Rover rover1 = marsConsumer.getManifestRover(MarsConsumer.rovers.curiosity);
        Rover rover2 = marsConsumer.getManifestRover(MarsConsumer.rovers.opportunity);
        Rover rover3 = marsConsumer.getManifestRover(MarsConsumer.rovers.spirit);
        cmbNames.getItems().addAll(rover1.getName(), rover2.getName(), rover3.getName());
    }

    @FXML
    public void m_onClickgetInformation() {
        String selectedRover = cmbNames.getSelectionModel().getSelectedItem();
        Rover rover = null;
        if (selectedRover != null) {
            switch (selectedRover) {
                case "Curiosity":
                    rover = marsConsumer.getManifestRover(MarsConsumer.rovers.curiosity);
                    break;
                case "Opportunity":
                    rover = marsConsumer.getManifestRover(MarsConsumer.rovers.opportunity);
                    break;
                case "Spirit":
                    rover = marsConsumer.getManifestRover(MarsConsumer.rovers.spirit);
                    break;
                default:
                    break;
            }
            name.setText("Nombre: " +rover.getName());
            landingDate.setText("Fecha de Aterrizaje: " +rover.getLandingDate().toString());
            launchDate.setText("Fceha de Lanzamiento: " +rover.getLaunchDate().toString());
            status.setText("Estatus: " +rover.getStatus().toString());
            maxSol.setText("Sol Maximo: " + rover.getMaxSol());
            maxDate.setText("Fecha Maxima: " + rover.getMaxDate());
            totalPhotos.setText("Total de fotos: " + rover.getTotalPhotos());

            try {
                int column = 0;
                int row = 0;
                Photos [] photos = marsConsumer.getLatest(rover.getName());
                ImageRetriever img;
                images.getChildren().clear();
                for (Photos photo : photos) {
                    VBox imageBox = new VBox();
                    img = new ImageRetriever();
                    ImageView imageView = new ImageView(img.getFromURL(photo.getImageSrc()));
                    imageView.setFitWidth(200);
                    imageView.setPreserveRatio(true);
                    imageBox.getChildren().add(imageView);

                    images.add(imageBox, column, row);

                    column++;
                    if (column == 10) {
                        column = 0;
                        row++;
                    }
                    System.out.println(photo.getImageSrc() + " Es link este");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onReturn(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main-view.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
    }

    public void setKey(String key){
        this.key = key;
    }
}

