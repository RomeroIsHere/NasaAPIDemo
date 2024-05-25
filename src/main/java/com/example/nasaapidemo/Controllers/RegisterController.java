package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import com.example.nasaapidemo.Models.MAPOD.APOD;
import com.example.nasaapidemo.Models.MAPOD.MediaType;
import com.example.nasaapidemo.Models.Users.TipoUser;
import com.example.nasaapidemo.Models.Users.User;
import com.example.nasaapidemo.Reports.APODItext;
import com.example.nasaapidemo.database.Dao.UserDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class RegisterController {
    @FXML
    TextField txt_User,txt_Password,txt_Code;
    List<APOD> a_apods=new ArrayList();

    UserDao a_userDao=new UserDao();

    @FXML
    private void onReturn(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Login-view.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
    }


    @FXML
    private void onClickSave(){

           if(m_veriCamps()){
               User v_user=new User();
               v_user.setCveTipoUser(new TipoUser());
               v_user.getCveTipoUser().setCveTipoUser("us");
               v_user.setUser(txt_User.getText());
               v_user.setPassword(txt_Password.getText());

               if(a_userDao.save(v_user))
                   m_showAlert("User saved Susseful", "Register complete", Alert.AlertType.INFORMATION);
               else
                   m_showAlert("There was an error with the conection try after", "Error conection", Alert.AlertType.ERROR);

           }

    }

    private boolean m_veriCamps(){
        boolean v_respuesta;
        v_respuesta=false;
        if(txt_Password.getText().isEmpty() || txt_User.getText().isEmpty() || txt_Code.getText().isEmpty())
                m_showAlert("Type All information plz", "Error", Alert.AlertType.ERROR);
             else if(!txt_Password.getText().equals(txt_Code.getText()))
            m_showAlert("Passwords Incorrects","Error", Alert.AlertType.ERROR);
             else
                 v_respuesta=true;

        return v_respuesta;
    }


    private void m_showAlert(String p_msg, String p_title, Alert.AlertType p_tipe){
        Alert v_alert=new Alert(p_tipe);
        v_alert.setContentText(p_msg);
        v_alert.setTitle(p_title);
        v_alert.show();
    }




}
