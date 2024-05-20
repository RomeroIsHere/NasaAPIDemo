package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import com.example.nasaapidemo.Models.MAPOD.APOD;
import com.example.nasaapidemo.Models.MAPOD.MediaType;
import com.example.nasaapidemo.Reports.APODItext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class APODController implements Initializable {
    @FXML
    TableView a_tblAPOD;
    List<APOD> a_listAPOD=new ArrayList();

    APODItext a_archivo=new APODItext();

   // APODDao a_APODDao=new APODDao();

    @FXML
    public void m_onClickgetInformation() {
    }

    @FXML
    public void m_onClickDelete() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private void m_onClickUpdate() throws IOException, URISyntaxException {
        List<APOD> v_list=new ArrayList();
        APOD v_apod;
        v_apod=new APOD();
        v_apod.setTitle("Jupiter Diving");
        v_apod.setDate("2024-05-19");
        v_apod.setUrl("https://api.nasa.gov/assets/img/general/apod.jpg");
        v_apod.setExplanation("Take this simulated plunge and dive into the upper atmosphere of Jupiter, the Solar System's ruling gas giant. The awesome animation is based on image data from JunoCam, and the microwave radiometer on board the Jupiter-orbiting Juno spacecraft. Your view will start about 3,000 kilometers above the southern Jovian cloud tops, and you can track your progress on the display at the left. As altitude decreases, temperature increases while you dive deeper at the location of Jupiter's famous Great Red Spot. In fact, Juno data indicates the Great Red Spot, the Solar System's largest storm system, penetrates some 300 kilometers into the giant planet's atmosphere. For comparison, the deepest point for planet Earth's oceans is just under 11 kilometers down. Don't worry though, you'll fly back out again.   Dive into the Universe: Random APOD Generator");
        v_apod.setCveMedia(new MediaType());
        v_apod.getCveMedia().setName("Image");
        v_list.add(v_apod);
        a_archivo.createPdf("results/temp.pdf",v_list);

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

