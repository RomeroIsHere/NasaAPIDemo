package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.Models.MAPOD.APOD;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class APODController implements Initializable {
    @FXML
    TableView a_tblAPOD;
    List<APOD> a_listAPOD=new ArrayList();

   // APODDao a_APODDao=new APODDao();

    @FXML
    public void m_onClickgetInformation() {
    }

    @FXML
    public void m_onClickDelete() {
    }

    @FXML
    public void m_onClickUpdate(){}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

