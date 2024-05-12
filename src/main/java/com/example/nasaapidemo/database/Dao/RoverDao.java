package com.example.nasaapidemo.database.Dao;

import com.example.nasaapidemo.Models.APOD;
import com.example.nasaapidemo.Models.MediaType;
import com.example.nasaapidemo.Models.Rover;
import com.example.nasaapidemo.Models.Status;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static com.example.nasaapidemo.database.MySQLConnection.getConnection;

public class RoverDao {
    Connection conn = getConnection();


    public List<Rover> findAll() {

        List<Rover> roverList = FXCollections.observableArrayList();
        String query = "select * from Rover inner join status";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Rover rover = new Rover();
                rover.setIdRover(rs.getInt("id"));
                rover.setTotalPhotos(rs.getInt("TotalPhotos"));
                rover.setMaxSol(rs.getInt("MaxSol"));
                rover.setIdStatus(getStatus(rs.getInt("Status")));
                rover.setName(rs.getString("Name"));
                rover.setLandingDate(rs.getString("LangingDate"));
                rover.setLaunchDate(rs.getString("LaunchDate"));
                rover.setMaxDate(rs.getString("MaxDate"));


                roverList.add(rover);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roverList;
    }

    public boolean save(Rover rover) {
        String query = "insert into Rover " +
                " (idRover, TotalPhotos, maxSol, idStatus, name, landingDate, launchDate, maxDate)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, rover.getIdRover());
            ps.setInt(2, rover.getTotalPhotos());
            ps.setInt(3, rover.getMaxSol());
            ps.setInt(4, rover.getIdStatus().getIdStatus());
            ps.setString(5, rover.getName());
            ps.setString(6, rover.getLandingDate());
            ps.setString(7, rover.getLaunchDate());
            ps.setString(8, rover.getMaxDate());

            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int status_id) {
        String query = "delete from Status where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,status_id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public Status getStatus(int idS)
    {
        String query = "select * from Status where idStatus ="+ idS;

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            Status status = new Status();
            status.setIdStatus(rs.getInt("id"));
            return status;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
