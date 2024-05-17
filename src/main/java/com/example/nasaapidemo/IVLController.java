package com.example.nasaapidemo;

import com.example.nasaapidemo.Models.MAPOD.APOD;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class IVLController implements Initializable {
    @FXML
    TextField txtBuscar;

    List<APOD> a_listAPOD=new ArrayList();

   // APODDao a_APODDao=new APODDao();

    @FXML
    public void m_onClickBuscar() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

