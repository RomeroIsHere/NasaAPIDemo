package com.example.nasaapidemo.database.Dao;

import com.example.nasaapidemo.Models.MAPOD.MediaType;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;

import static com.example.nasaapidemo.database.MySQLConnection.getConnection;

public class MediaTypeDao {
    Connection conn = getConnection();


    public List<MediaType> findAll() {

        List<MediaType> mediaList = FXCollections.observableArrayList();
        String query = "select * from media_type";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                MediaType media = new MediaType();
                media.setCveMedia(rs.getInt("cveMedia"));
                media.setName(rs.getString("name"));

                mediaList.add(media);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mediaList;
    }

    public boolean save(MediaType media) {
        String query = "insert into media_type " +
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
        String query = "delete from media_type where cveMedia = "+media;
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
