package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import com.example.nasaapidemo.Models.MAPOD.APOD;
import com.example.nasaapidemo.Reports.APODItext;
import com.example.nasaapidemo.apicontroller.APODConsumer;
import com.example.nasaapidemo.database.Dao.APODDao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class ApodDBController implements Initializable {

    boolean flag;

    APODItext a_archivo=new APODItext();

    @FXML
    TableView<APOD> tbl_Apods;

    List<APOD> records = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        APODDao apodDao = new APODDao();
        records = apodDao.findAll();
        tbl_Apods.setItems(FXCollections.observableArrayList(records));
        tbl_Apods.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    @FXML
    private void onReturn(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main-view.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        MainController mainController = fxmlLoader.getController();
        mainController.desactivar(flag);

    }

    @FXML
    private void onPDF() throws Exception {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date sevenDaysAgoDate = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormat.format(currentDate);
        String sevenDaysAgoDateTime = dateFormat.format(sevenDaysAgoDate);
        APODConsumer apod = new APODConsumer();
        APOD arr[] = apod.getByDateRange(sevenDaysAgoDateTime, currentDateTime);
        List<APOD> v_list = Arrays.asList(arr);
        a_archivo.createPdf("results/temp.pdf",v_list);
    }

    public void getFlag(boolean flag){
        this.flag=flag;
    }
}
