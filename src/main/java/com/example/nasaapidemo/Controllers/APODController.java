package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import com.example.nasaapidemo.Models.MAPOD.APOD;
import com.example.nasaapidemo.Models.MAPOD.MediaType;
import com.example.nasaapidemo.Reports.APODItext;
import com.example.nasaapidemo.apicontroller.APODConsumer;
import com.example.nasaapidemo.apicontroller.ImageRetriever;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class APODController implements Initializable {

    @FXML
    HBox contenedor;

    @FXML
    Label title, date, urlIMG, explanation;

    @FXML
    TableView a_tblAPOD;
    List<APOD> a_listAPOD=new ArrayList();

    APOD apod;

    APODItext a_archivo=new APODItext();

   // APODDao a_APODDao=new APODDao();

    @FXML
    public void m_onClickgetInformation() {
    }

    @FXML
    public void m_onClickDelete() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        APODConsumer APODConsumer=new APODConsumer("fk5hNwja1lbzQEY3QbMoRpuDoqGnUgmhVYT9V1ou");
        Date currentDate=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime= dateFormat.format(currentDate);

        apod = APODConsumer.getByDateString(dateTime);

        System.out.println(apod.getCveMedia());
        try{
            ImageRetriever img = new ImageRetriever();
            ImageView imageView = new ImageView(img.getFromURL(apod.getUrl()));
            imageView.setFitWidth(300);
            imageView.setPreserveRatio(true);
            contenedor.getChildren().add(imageView);
            title.setText(apod.getTitle());
            date.setText(apod.getDate());
            urlIMG.setText(apod.getUrl());
            explanation.setText(apod.getExplanation());
        }
        catch (Exception e){

        }
    }


    @FXML
    private void m_onClickUpdate() throws IOException, URISyntaxException {
        List<APOD> v_list=new ArrayList();
        v_list.add(apod);
        a_archivo.createPdf("results/temp.pdf",v_list);

    }

    @FXML
    private void onReturn(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main-view.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}

