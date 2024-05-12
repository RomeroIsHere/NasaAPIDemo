package com.example.nasaapidemo.database.Dao;

import com.example.nasaapidemo.Models.*;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static com.example.nasaapidemo.database.MySQLConnection.getConnection;

public class PhotosDao {
    Connection conn = getConnection();


    public List<Photos> findAll() {

        List<Photos> photosList = FXCollections.observableArrayList();
        String query = "select * from Photos inner join camera inner join rover";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Photos photos = new Photos();
                photos.setId(rs.getInt("id"));
                photos.setSol(rs.getInt("sol"));
                photos.setIdCamera(getCamera(rs.getInt("IdCamera")));
                photos.setIdRover(getRover(rs.getInt("IdRover")));
                photos.setImageSrc(rs.getString("imageSrc"));
                photos.setEarthDate(rs.getString("EarthDate"));

                photosList.add(photos);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return photosList;
    }

    public boolean save(Photos photos) {
        String query = "insert into Photos " +
                " (id,sol,idCamera,idRover,imageSrc,earthDate)" +
                " values (?, ?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, photos.getId());
            ps.setInt(2, photos.getSol());
            ps.setInt(3, photos.getIdCamera().getId());
            ps.setInt(4, photos.getIdRover().getIdRover());
            ps.setString(5, photos.getImageSrc());
            ps.setString(6, photos.getEarthDate());

            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String query = "delete from Photos where id = "+ id;
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
        String query = "select * from camera where idNasa ="+ idc;

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
        String query = "select * from Rover where id ="+ idr;

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            Rover rover = new Rover();
            rover.setIdRover(rs.getInt("idRover"));
            return rover;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
