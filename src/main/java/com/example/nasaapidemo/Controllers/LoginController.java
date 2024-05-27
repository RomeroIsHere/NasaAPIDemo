package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import com.example.nasaapidemo.database.Dao.UserDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class LoginController {

    @FXML
    TextField txtUser, txtPassword;


    UserDao a_userDao=new UserDao();

    public void m_onDirigir(javafx.event.ActionEvent actionEvent) throws Exception{
        boolean flag=false;
        if(m_ceriDatas()){

            if(a_userDao.findbyUser(txtPassword.getText(),txtUser.getText())){
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main-view.fxml"));
            ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
                MainController marsController = fxmlLoader.getController();
                marsController.desactivar(flag);
            } else
                m_showAlert("Password or user incorrect","Error login", Alert.AlertType.ERROR);

        }else
            m_showAlert("Type all information plz","Error", Alert.AlertType.ERROR);
    }

    @FXML
    public void onInvitado(javafx.event.ActionEvent actionEvent) throws Exception{
        boolean flag = true;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main-view.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        MainController marsController = fxmlLoader.getController();
        marsController.desactivar(flag);
    }


    private boolean m_ceriDatas(){
        boolean v_respuesta;

        if(!txtPassword.getText().isEmpty() && !txtUser.getText().isEmpty())
            v_respuesta=true;
        else
            v_respuesta=false;

        return v_respuesta;
    }



    public void onRegist(javafx.event.ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Register-view.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
    }

    private void m_showAlert(String p_msg, String p_title, Alert.AlertType p_tipe){
        Alert v_alert=new Alert(p_tipe);
        v_alert.setContentText(p_msg);
        v_alert.setTitle(p_title);
        v_alert.show();
    }




}
