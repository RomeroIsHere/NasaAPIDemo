package com.example.nasaapidemo.database.Dao;

import com.example.nasaapidemo.Models.*;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static com.example.nasaapidemo.database.MySQLConnection.getConnection;

public class EquipoDao {
    Connection conn = getConnection();


    public Optional<Equipo> findById(int id) {
        return Optional.empty();
    }

    public List<Equipo> findAll() {

        List<Equipo> equipoList = FXCollections.observableArrayList();
        String query = "select * from Equipo inner join camera inner join rover";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Equipo equipo = new Equipo();
                equipo.setIdCamera(getCamera(rs.getInt("IdCamera")));
                equipo.setIdRover(getRover(rs.getInt("IdRover")));

                equipoList.add(equipo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return equipoList;
    }

    public boolean save(Equipo equipo) {
        String query = "insert into Equipo " +
                " (idCamera,idRover)" +
                " values (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, equipo.getIdCamera().getId());
            ps.setInt(2, equipo.getIdRover().getIdRover());

            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int nasa_id, String key) {
        String query = "delete from autos where idNasa = "+nasa_id+"and "+"name= "+"'"+key+"'";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,nasa_id);
            ps.setString(2,key);
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
