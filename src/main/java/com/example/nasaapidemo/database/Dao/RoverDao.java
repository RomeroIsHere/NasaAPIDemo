package com.example.nasaapidemo.database.Dao;

import com.example.nasaapidemo.Models.MMars.Rover;
import com.example.nasaapidemo.Models.MMars.Status;
import javafx.collections.FXCollections;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.example.nasaapidemo.database.MySQLConnection.getConnection;

public class RoverDao {
    Connection conn = getConnection();


    public List<Rover> findAll() {

        List<Rover> roverList = FXCollections.observableArrayList();
        String query = "select * from rover inner join status";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Rover rover = new Rover();
                rover.setIdRover(rs.getInt("id"));
                rover.setName(rs.getString("name"));
                rover.setTotalPhotos(rs.getInt("total_photos"));
                rover.setLandingDate(rs.getDate("landing_date"));
                rover.setLaunchDate(rs.getDate("launch_date"));
                rover.setMaxSol(rs.getInt("max_sol"));
                rover.setMaxDate(rs.getDate("max_date"));
                rover.setIdStatus(getStatus(rs.getInt("idStatus")));

                roverList.add(rover);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roverList;
    }

    public boolean save(Rover rover) {
        String query = "insert into rover " +
                " (id, name, total_photos, landing_date, launch_date, max_sol, max_date, idStatus)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, rover.getIdRover());
            ps.setString(2, rover.getName());
            ps.setInt(3, rover.getTotalPhotos());
            ps.setString(4, formatter.format(rover.getLandingDate()));
            ps.setString(5, formatter.format(rover.getLaunchDate()));
            ps.setInt(6, rover.getMaxSol());
            ps.setString(7, formatter.format(rover.getMaxDate()));
            ps.setInt(8, rover.getIdStatus().getIdStatus());

            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int status_id) {
        String query = "delete from rover where id = ?";
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
        String query = "select * from status where id="+ idS;

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
