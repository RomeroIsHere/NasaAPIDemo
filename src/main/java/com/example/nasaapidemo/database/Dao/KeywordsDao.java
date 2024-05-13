package com.example.nasaapidemo.database.Dao;

import com.example.nasaapidemo.Models.APOD;
import com.example.nasaapidemo.Models.Keywords;
import com.example.nasaapidemo.Models.MediaType;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static com.example.nasaapidemo.database.MySQLConnection.getConnection;


public class KeywordsDao {

    Connection conn = getConnection();


    public List<Keywords> findAll() {

        List<Keywords> keyList = FXCollections.observableArrayList();
        String query = "select * from keywords";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Keywords key = new Keywords();
                key.setName(rs.getString("name"));

                keyList.add(key);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return keyList;
    }

    public boolean save(Keywords key) {
        String query = "insert into keywords " +
                " (name)" +
                " values (?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, key.getName());

            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String key) {
        String query = "delete from keyword where name = "+"'"+key+"'";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,key);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
