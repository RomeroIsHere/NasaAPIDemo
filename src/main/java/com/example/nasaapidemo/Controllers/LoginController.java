package com.example.nasaapidemo.Controllers;

import com.example.nasaapidemo.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;


public class LoginController {

    @FXML
    TextField txtUser, txtPassword;


    public void m_onDirigir(javafx.event.ActionEvent actionEvent) throws Exception{



        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main-view.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
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





}
