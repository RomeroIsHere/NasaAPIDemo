package com.example.nasaapidemo.database.Dao;

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
                user.setGmail(rs.getString("gmail"));
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getInt("administer"));


                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }



    public boolean save(User user) {
        String query = "insert into autos " +
                " (gmail, user, password, administer)" +
                " values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getGmail());
            ps.setString(2, user.getUser());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getAdmin());
            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public List<User> findbyUser(String gmail, String name) {

        List<User> userList = FXCollections.observableArrayList();
        String query = "select * from user where gmail= "+ "'"+gmail+"' and user= "+"'"+name+"'";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                User user = new User();
                user.setGmail(rs.getString("gmail"));
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getInt("administer"));

                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }








}
