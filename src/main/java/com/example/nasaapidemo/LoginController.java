package com.example.nasaapidemo;

import com.example.nasaapidemo.Models.APOD;
import com.example.nasaapidemo.Models.MediaType;
import com.example.nasaapidemo.Reports.APODItext;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    @FXML
    private Label welcomeText;

    List<APOD> a_apods=new ArrayList();
    @FXML
    protected void onHelloButtonClick() throws IOException {
        welcomeText.setText("Welcome to JavaFX Application!");
        m_temporal();
        APODItext v_nuevo=new APODItext();
        v_nuevo.createPdf("results/temp1.pdf",a_apods);
    }


    private void m_temporal(){
        APOD v_apod=new APOD();
        v_apod.setDate("2024-05-11");
        v_apod.setExplanation("Right now, one of the largest sunspot groups in recent history is crossing the Sun. Active Region 3664 is not only big -- it's violent, throwing off clouds of particles into the Solar System. Some of these CMEs are already impacting the Earth, and others might follow.  At the extreme, these solar storms could cause some Earth-orbiting satellites to malfunction, the Earth's atmosphere to slightly distort, and electrical power grids to surge. When impacting Earth's upper atmosphere, these particles can produce beautiful auroras, with some auroras already being reported unusually far south.  Pictured here, AR3664 and its dark sunspots were captured yesterday in visible light from Rome, Italy. The AR3664 sunspot group is so large that it is visible just with glasses designed to view last month's total solar eclipse.  This weekend, skygazing enthusiasts will be keenly watching the night skies all over the globe for bright and unusual auroras.   Gallery: Active Region 3664 on the Sun and Associated Aurora\",\n" +
                "  \"hdurl\": \"https://apod.nasa.gov/apod/image/2405/SunAr3664_Fantasia_3216.jpg");
        v_apod.setHdUrl("https://apod.nasa.gov/apod/image/2405/SunAr3664_Fantasia_3216.jpg");
        v_apod.setCveMedia(new MediaType());
        v_apod.getCveMedia().setName("image");
        v_apod.setServiceVersion("v1");
        v_apod.setUrl("https://apod.nasa.gov/apod/image/2405/SunAr3664_Fantasia_960.jpg");
        a_apods.add(v_apod);
    }

}