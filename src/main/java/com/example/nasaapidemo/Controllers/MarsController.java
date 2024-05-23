package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import com.example.nasaapidemo.Models.MMars.Rover;
import com.example.nasaapidemo.apicontroller.MarsConsumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MarsController implements Initializable {

    @FXML
    private ComboBox<String> cmbNames;

    @FXML
    private Label name, landingDate, launchDate, status, maxSol, maxDate, totalPhotos;

    MarsConsumer marsConsumer=new MarsConsumer("fk5hNwja1lbzQEY3QbMoRpuDoqGnUgmhVYT9V1ou");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Rover rover1 = marsConsumer.getManifestRover(MarsConsumer.rovers.curiosity);
        Rover rover2 = marsConsumer.getManifestRover(MarsConsumer.rovers.opportunity);
        Rover rover3 = marsConsumer.getManifestRover(MarsConsumer.rovers.spirit);
        cmbNames.getItems().addAll(rover1.getName(), rover2.getName(), rover3.getName());
    }

    @FXML
    public void m_onClickgetInformation() {
        String selectedRover = cmbNames.getSelectionModel().getSelectedItem();
        Rover rover;
        if (selectedRover != null) {
            switch (selectedRover) {
                case "Curiosity":
                    rover = marsConsumer.getManifestRover(MarsConsumer.rovers.curiosity);
                    name.setText("Nombre: " +rover.getName());
                    landingDate.setText("Fecha de Aterrisage: " +rover.getLandingDate().toString());
                    launchDate.setText("Fceha de Lanzamiento: " +rover.getLaunchDate().toString());
                    status.setText("Estatus: " +rover.getStatus().toString());
                    maxSol.setText("Sol Maximo: " + rover.getMaxSol());
                    maxDate.setText("Fecha Maxima: " + rover.getMaxDate());
                    totalPhotos.setText("Total de fotos: " + rover.getTotalPhotos());
                    break;
                case "Opportunity":
                    rover = marsConsumer.getManifestRover(MarsConsumer.rovers.opportunity);
                    name.setText("Nombre: " +rover.getName());
                    landingDate.setText("Fecha de Aterrisage: " +rover.getLandingDate().toString());
                    launchDate.setText("Fceha de Lanzamiento: " +rover.getLaunchDate().toString());
                    status.setText("Estatus: " +rover.getStatus().toString());
                    maxSol.setText("Sol Maximo: " + rover.getMaxSol());
                    maxDate.setText("Fecha Maxima: " + rover.getMaxDate());
                    totalPhotos.setText("Total de fotos: " + rover.getTotalPhotos());
                    break;
                case "Spirit":
                    rover = marsConsumer.getManifestRover(MarsConsumer.rovers.spirit);
                    name.setText("Nombre: " +rover.getName());
                    landingDate.setText("Fecha de Aterrisage: " +rover.getLandingDate().toString());
                    launchDate.setText("Fceha de Lanzamiento: " +rover.getLaunchDate().toString());
                    status.setText("Estatus: " +rover.getStatus().toString());
                    maxSol.setText("Sol Maximo: " + rover.getMaxSol());
                    maxDate.setText("Fecha Maxima: " + rover.getMaxDate());
                    totalPhotos.setText("Total de fotos: " + rover.getTotalPhotos());
                    break;
                default:
                    break;
            }
        }
    }

    @FXML
    private void onReturn(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main-view.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}

