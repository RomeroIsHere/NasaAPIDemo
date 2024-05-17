package com.example.nasaapidemo.database.Dao;

import com.example.nasaapidemo.Models.MMars.Status;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;

import static com.example.nasaapidemo.database.MySQLConnection.getConnection;

public class StatusDao {
    Connection conn = getConnection();


    public List<Status> findAll() {

        List<Status> statusList = FXCollections.observableArrayList();
        String query = "select * from status";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Status status = new Status();
                status.setIdStatus(rs.getInt("id"));
                status.setStatus(rs.getString("Status"));

                statusList.add(status);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statusList;
    }

    public boolean save(Status status) {
        String query = "insert into Status " +
                " (id, status)" +
                " values (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, status.getIdStatus());
            ps.setString(2, status.getStatus());

            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int status_id) {
        String query = "delete from status where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,status_id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
