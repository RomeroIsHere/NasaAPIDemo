package com.example.nasaapidemo.database.Dao;

import com.example.nasaapidemo.Models.MMars.Camera;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static com.example.nasaapidemo.database.MySQLConnection.getConnection;

public class CameraDao {
    Connection conn = getConnection();


    public Optional<Camera> findById(int id) {
        return Optional.empty();
    }

    public List<Camera> findAll() {

        List<Camera> cameraList = FXCollections.observableArrayList();
        String query = "select * from camera";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Camera camera = new Camera();
                camera.setId(rs.getInt("id"));
                camera.setNombre(rs.getString("name"));
                camera.setFullName(rs.getString("full_name"));

                cameraList.add(camera);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cameraList;
    }

    public boolean save(Camera camera) {
        String query = "insert into camera " +
                " (id, name, full_name)" +
                " values (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, camera.getId());
            ps.setString(2, camera.getNombre());
            ps.setString(3, camera.getFullName());


            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int camera_id) {
        String query = "delete from camera where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,camera_id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
