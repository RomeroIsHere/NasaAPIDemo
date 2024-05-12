package com.example.nasaapidemo.database.Dao;


import com.example.nasaapidemo.Models.APOD;
import com.example.nasaapidemo.Models.MediaType;
import com.example.nasaapidemo.database.MySQLConnection;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class APODDao extends MySQLConnection{
    Connection conn = getConnection();


    public Optional<APOD> findById(int id) {
        return Optional.empty();
    }

    public List<APOD> findAll() {

        List<APOD> apodList = FXCollections.observableArrayList();
        String query = "select * from APOD inner join MediaType";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                APOD apod = new APOD();
                apod.setId(rs.getInt("id"));
                apod.setCveMedia(getCveMedia(rs.getInt("MediaType")));
                apod.setDate(rs.getString("Date"));
                apod.setExplanation(rs.getString("Explanation"));
                apod.setUrl(rs.getString("Url"));
                apod.setHdUrl(rs.getString("HdUrl"));
                apod.setThumbnailUrl(rs.getString("ThumbnailUrl"));
                apod.setServiceVersion(rs.getString("ServiceVersion"));


                apodList.add(apod);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return apodList;
    }

    public boolean save(APOD apod) {
        String query = "insert into apod " +
                " (id, MediaType, Date, Explanation, Url, HdUrl, ThumbnailUrl, ServiceVersion)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, apod.getId());
            ps.setInt(2, apod.getCveMedia().getCveMedia());
            ps.setString(3, apod.getDate());
            ps.setString(4, apod.getExplanation());
            ps.setString(5, apod.getUrl());
            ps.setString(6, apod.getHdUrl());
            ps.setString(7, apod.getThumbnailUrl());
            ps.setString(8, apod.getServiceVersion());

            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int apod_id) {
        String query = "delete from APOD where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,apod_id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public MediaType getCveMedia(int idM)
    {
        String query = "select * from MediaType where id ="+ idM;

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            MediaType media = new MediaType();
            media.setCveMedia(rs.getInt("id"));
            return media;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}

