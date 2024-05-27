package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import com.example.nasaapidemo.Models.MIVL.Item;
import com.example.nasaapidemo.Models.MMars.Photos;
import com.example.nasaapidemo.Models.MMars.Rover;
import com.example.nasaapidemo.apicontroller.ImageRetriever;
import com.example.nasaapidemo.apicontroller.MarsConsumer;
import com.example.nasaapidemo.database.Dao.RoverDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MarsController implements Initializable {

    @FXML
    private ComboBox<String> cmbNames;

    @FXML
    private Label name, landingDate, launchDate, status, maxSol, maxDate, totalPhotos;

    @FXML
    private GridPane images;

    Rover rover = null;

    RoverDao roverDao=new RoverDao();

    Button save;

    MarsConsumer marsConsumer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void m_onClickgetInformation() {
        String selectedRover = cmbNames.getSelectionModel().getSelectedItem();

        if (selectedRover != null) {
            try{
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
                launchDate.setText("Fecha de Lanzamiento: " +rover.getLaunchDate().toString());
                status.setText("Estatus: " +rover.getStatus().toString());
                maxSol.setText("Sol Maximo: " + rover.getMaxSol());
                maxDate.setText("Fecha Maxima: " + rover.getMaxDate());
                totalPhotos.setText("Total de fotos: " + rover.getTotalPhotos());
            }catch (Exception e){
                //Aqui agrega la logica de creacion de Mensaje de Error
            }

            try {
                int column = 0;
                int row = 0;
                Photos [] photos = marsConsumer.getLatest(rover.getName());
                ImageRetriever img=new ImageRetriever();
                images.getChildren().clear();
                for (Photos photo : photos) {
                    //VBox imageBox = new VBox();
                    ImageView imageView = new ImageView(img.getFromURL(photo.getImageSrc()));
                    imageView.setFitWidth(200);
                    imageView.setPreserveRatio(true);
                    //imageBox.getChildren().add(imageView);

                    images.add(imageView, column, row);

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

    public void setAPIKey(String text) throws Exception{
        //"fk5hNwja1lbzQEY3QbMoRpuDoqGnUgmhVYT9V1ou"
        if (!text.isEmpty()&&!text.isBlank())
            marsConsumer=new MarsConsumer(text);
        else
            marsConsumer=new MarsConsumer();
        try {
            Rover rover1 = marsConsumer.getManifestRover(MarsConsumer.rovers.curiosity);
            Rover rover2 = marsConsumer.getManifestRover(MarsConsumer.rovers.opportunity);
            Rover rover3 = marsConsumer.getManifestRover(MarsConsumer.rovers.spirit);
            cmbNames.getItems().addAll(rover1.getName(), rover2.getName(), rover3.getName());
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("La coneccion es inestable y no puede cargar los datos");
            alert.show();
        }
    }

    @FXML
    private void m_save(){
        roverDao.save(rover);
    }
}

