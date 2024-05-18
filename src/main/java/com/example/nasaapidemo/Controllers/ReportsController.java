package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.Models.MAPOD.APOD;
import com.example.nasaapidemo.Models.MAPOD.MediaType;
import com.example.nasaapidemo.Reports.APODItext;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportsController {
    @FXML
    TextField txt_nameFile;
    List<APOD> a_apods=new ArrayList();
    @FXML
    protected void onReportonClick() throws IOException {
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
        a_apods.add(v_apod);
        System.out.println(v_apod.getExplanation());
    }
}
