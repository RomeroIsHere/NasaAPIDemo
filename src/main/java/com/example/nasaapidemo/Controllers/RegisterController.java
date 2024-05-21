package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import com.example.nasaapidemo.Models.MAPOD.APOD;
import com.example.nasaapidemo.Models.MAPOD.MediaType;
import com.example.nasaapidemo.Reports.APODItext;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class RegisterController {
    @FXML
    TextField txt_nameFile;
    List<APOD> a_apods=new ArrayList();
    @FXML
    protected void onReportonClick() throws IOException, URISyntaxException {
        m_temporal();
        APODItext v_nuevo=new APODItext();
        v_nuevo.createPdf("results/temp1.pdf",a_apods);
    }

    private void m_temporal(){
        APOD v_apod=new APOD();
        v_apod.setDate("2024-05-11");
        v_apod.setHdUrl("https://apod.nasa.gov/apod/image/2405/SunAr3664_Fantasia_3216.jpg");
        v_apod.setCveMedia(new MediaType());
        v_apod.getCveMedia().setName("image");
        v_apod.setServiceVersion("v1");
        v_apod.setUrl("https://apod.nasa.gov/apod/image/2405/SunAr3664_Fantasia_960.jpg");
        v_apod.setTitle("North Celestial Aurora");
        v_apod.setExplanation("Graceful star trail arcs reflect planet Earth's daily rotation in this colorful night skyscape. To create the timelapse composite, on May 12 consecutive exposures were recorded with a camera fixed to a tripod on the shores of the Ashokan Reservoir, in the Catskills region of New York, USA. North star Polaris is near the center of the star trail arcs. The broad trail of a waxing crescent Moon is on the left, casting a strong reflection across the reservoir waters. With intense solar activity driving recent geomagnetic storms, the colorful aurora borealis or northern lights, rare to the region, shine under Polaris and the north celestial pole.   AuroraSaurus: Report your aurora observations");
        a_apods.add(v_apod);
        v_apod=new APOD();
        v_apod.setDate("2024-05-11");
        v_apod.setHdUrl("https://apod.nasa.gov/apod/image/2405/SunAr3664_Fantasia_3216.jpg");
        v_apod.setCveMedia(new MediaType());
        v_apod.getCveMedia().setName("image");
        v_apod.setServiceVersion("v1");
        v_apod.setUrl("https://apod.nasa.gov/apod/image/2405/SunAr3664_Fantasia_960.jpg");
        v_apod.setTitle("North Celestial Aurora");
        v_apod.setExplanation("This is temporal");
        a_apods.add(v_apod);
         v_apod=new APOD();
        v_apod.setDate("2024-05-11");
        v_apod.setHdUrl("https://apod.nasa.gov/apod/image/2405/SunAr3664_Fantasia_3216.jpg");
        v_apod.setCveMedia(new MediaType());
        v_apod.getCveMedia().setName("image");
        v_apod.setServiceVersion("v1");
        v_apod.setUrl("https://apod.nasa.gov/apod/image/2405/SunAr3664_Fantasia_960.jpg");
        v_apod.setTitle("North Celestial Aurora");
        v_apod.setExplanation("Graceful star trail arcs reflect planet Earth's daily rotation in this colorful night skyscape. To create the timelapse composite, on May 12 consecutive exposures were recorded with a camera fixed to a tripod on the shores of the Ashokan Reservoir, in the Catskills region of New York, USA. North star Polaris is near the center of the star trail arcs. The broad trail of a waxing crescent Moon is on the left, casting a strong reflection across the reservoir waters. With intense solar activity driving recent geomagnetic storms, the colorful aurora borealis or northern lights, rare to the region, shine under Polaris and the north celestial pole.   AuroraSaurus: Report your aurora observations");



        a_apods.add(v_apod);
    }

    @FXML
    private void onReturn(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main-view.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
