package com.example.nasaapidemo.database.Dao;

import com.example.nasaapidemo.Models.Users.TipoUser;
import com.example.nasaapidemo.Models.Users.User;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static com.example.nasaapidemo.database.MySQLConnection.getConnection;

public class UserDao {
    Connection conn = getConnection();


    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    public List<User> findAll() {

        List<User> userList = FXCollections.observableArrayList();
        String query = "select * from user";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                User user = new User();
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                user.setCveTipoUser(getCveTipoUsuario(rs.getString("cveTipoUsuario")));

                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }



    public boolean save(User user) {
        String query = "insert into user " +
                " (user, password, cveTipoUsuario)" +
                " values (?, MD5(?), ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getUser());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getCveTipoUser().getCveTipoUser());
            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public Boolean findbyUser(String passw, String name) {
        boolean exist=false;
        List<User> userList = FXCollections.observableArrayList();
        String query = "select count(*) as count from user where password in(select MD5("+"'"+passw+"'"+")) AND user = "+"'"+name+"'";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if(rs.next()){

                if(rs.getInt("count")==1)
                    exist=true;

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exist;
    }


    public TipoUser getCveTipoUsuario(String tipo)
    {
        String query = "select * from tipouser where cveTipoUser = "+ "'"+tipo+"'";

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            TipoUser tipoUser = new TipoUser();
            tipoUser.setCveTipoUser(rs.getString("cveTipoUser"));
            return tipoUser;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }







}
