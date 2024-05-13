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
        String query = "select * from apod inner join media_type";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                APOD apod = new APOD();
                apod.setId(rs.getInt("id"));
                apod.setDate(rs.getString("date"));
                apod.setExplanation(rs.getString("explanation"));
                apod.setUrl(rs.getString("url"));
                apod.setHdUrl(rs.getString("HDurl"));
                apod.setThumbnailUrl(rs.getString("thumbnail_url"));
                apod.setServiceVersion(rs.getString("service_version"));
                apod.setCveMedia(getCveMedia(rs.getInt("cveMedia")));


                apodList.add(apod);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return apodList;
    }

    public boolean save(APOD apod) {
        String query = "insert into apod " +
                " (id, date, explanation, url, HDurl, thumbnail_url, service_version, cveMedia)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, apod.getId());
            ps.setString(2, apod.getDate());
            ps.setString(3, apod.getExplanation());
            ps.setString(4, apod.getUrl());
            ps.setString(5, apod.getHdUrl());
            ps.setString(6, apod.getThumbnailUrl());
            ps.setString(7, apod.getServiceVersion());
            ps.setInt(8, apod.getCveMedia().getCveMedia());

            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int apod_id) {
        String query = "delete from apod where id = ?";
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
        String query = "select * from media_type where cveMedia ="+ idM;

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            MediaType media = new MediaType();
            media.setCveMedia(rs.getInt("cveMedia"));
            return media;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}

