package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import com.example.nasaapidemo.Models.MAPOD.APOD;
import com.example.nasaapidemo.Models.MAPOD.MediaType;
import com.example.nasaapidemo.Models.MMars.Rover;
import com.example.nasaapidemo.Reports.APODItext;
import com.example.nasaapidemo.apicontroller.APODConsumer;
import com.example.nasaapidemo.apicontroller.ImageRetriever;
import com.example.nasaapidemo.apicontroller.MarsConsumer;
import com.example.nasaapidemo.database.Dao.APODDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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
    Label title, date, urlIMG;

    @FXML
    TextArea explanation;

    List<APOD> a_listAPOD=new ArrayList();

    APOD apod;

    boolean flag;

    APODItext a_archivo=new APODItext();

    APODConsumer APODConsumer;

    APODDao apodDao=new APODDao();




    public void initialize(URL url, ResourceBundle resourceBundle){
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
        MainController mainController = fxmlLoader.getController();
        mainController.desactivar(flag);
    }

    public void setAPIKey(String text) {
        //"fk5hNwja1lbzQEY3QbMoRpuDoqGnUgmhVYT9V1ou"
        explanation.setWrapText(true);
        if (!text.isEmpty()&&!text.isBlank())
            APODConsumer =new APODConsumer(text);
        else
            APODConsumer=new APODConsumer();
        Date currentDate=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime= dateFormat.format(currentDate);

        apod = APODConsumer.getByDateString(dateTime);

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
    private void m_save(){
        apod.setCveMedia(new MediaType());
        apod.getCveMedia().setCveMedia(1);
        apodDao.save(apod);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Informacion Guardada");
        alert.show();
    }


    @FXML
    private void m_openDataBase(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("APODDB-view.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        ApodDBController apodDBController = fxmlLoader.getController();
        apodDBController.getFlag(flag);
    }

    public void getFlag(boolean flag){
        this.flag=flag;
    }
}

