package com.example.nasaapidemo.database.Dao;

import com.example.nasaapidemo.Models.MMars.Camera;
import com.example.nasaapidemo.Models.MMars.Photos;
import com.example.nasaapidemo.Models.MMars.Rover;
import javafx.collections.FXCollections;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.example.nasaapidemo.database.MySQLConnection.getConnection;

public class PhotosDao {
    Connection conn = getConnection();


    public List<Photos> findAll() {

        List<Photos> photosList = FXCollections.observableArrayList();
        String query = "select * from photos inner join camera inner join rover";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Photos photos = new Photos();
                photos.setId(rs.getInt("id"));
                photos.setSol(rs.getInt("sol"));
                photos.setEarthDate(rs.getDate("earth_date"));
                photos.setImageSrc(rs.getString("image_src"));
                photos.setIdCamera(getCamera(rs.getInt("IdCamera")));
                photos.setIdRover(getRover(rs.getInt("IdRover")));

                photosList.add(photos);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return photosList;
    }

    public boolean save(Photos photos) {
        String query = "insert into photos " +
                " (id,sol,earth_date,image_src,idCamera,idRover)" +
                " values (?, ?,?,?,?,?)";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, photos.getId());
            ps.setInt(2, photos.getSol());
            ps.setString(3, formatter.format(photos.getEarthDate()));
            ps.setString(4, photos.getImageSrc());
            ps.setInt(5, photos.getIdCamera().getId());
            ps.setInt(6, photos.getIdRover().getIdRover());

            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String query = "delete from photos where id = "+ id;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public Camera getCamera(int idc)
    {
        String query = "select * from camera where id ="+ idc;

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            Camera camera = new Camera();
            camera.setId(rs.getInt("id"));
            return camera;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Rover getRover(int idr)
    {
        String query = "select * from rover where id ="+ idr;

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            Rover rover = new Rover();
            rover.setIdRover(rs.getInt("id"));
            return rover;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
