package com.example.nasaapidemo.database.Dao;

import com.example.nasaapidemo.Models.Keywords;
import com.example.nasaapidemo.Models.MediaType;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;

import static com.example.nasaapidemo.database.MySQLConnection.getConnection;

public class MediaTypeDao {
    Connection conn = getConnection();


    public List<MediaType> findAll() {

        List<MediaType> mediaList = FXCollections.observableArrayList();
        String query = "select * from MediaType";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                MediaType media = new MediaType();
                media.setCveMedia(rs.getInt("CveMedia"));
                media.setName(rs.getString("Name"));

                mediaList.add(media);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mediaList;
    }

    public boolean save(MediaType media) {
        String query = "insert into MediaType " +
                " (cveMedia,name)" +
                " values (?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, media.getCveMedia());
            ps.setString(2,media.getName());

            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int media) {
        String query = "delete from MediaType where cveMedia = "+media;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,media);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
