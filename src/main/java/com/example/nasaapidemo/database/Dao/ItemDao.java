package com.example.nasaapidemo.database.Dao;

import com.example.nasaapidemo.Models.APOD;
import com.example.nasaapidemo.Models.Item;
import com.example.nasaapidemo.Models.MediaType;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static com.example.nasaapidemo.database.MySQLConnection.getConnection;

public class ItemDao {
    Connection conn = getConnection();


    public Optional<Item> findById(int id) {
        return Optional.empty();
    }

    public List<Item> findAll() {

        List<Item> itemList = FXCollections.observableArrayList();
        String query = "select * from Item inner join MediaType";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Item item = new Item();
                item.setIdNasa(rs.getInt("id"));
                item.setCveMedia(getCveMedia(rs.getInt("MediaType")));
                item.setHref(rs.getString("Href"));
                item.setCenter(rs.getString("Center"));
                item.setTitle(rs.getString("Title"));
                item.setPhotographer(rs.getString("Photographer"));
                item.setLocation(rs.getString("Location"));
                item.setDateCreation(rs.getString("DateCreation"));

                itemList.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    public boolean save(Item item) {
        String query = "insert into Item " +
                " (id, MediaType, Href, Center, Title, Photographer, Location, DateCreation)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, item.getIdNasa());
            ps.setInt(2, item.getCveMedia().getCveMedia());
            ps.setString(3, item.getHref());
            ps.setString(4, item.getCenter());
            ps.setString(5, item.getTitle());
            ps.setString(6, item.getPhotographer());
            ps.setString(7, item.getLocation());
            ps.setString(8, item.getDateCreation());

            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int item_id) {
        String query = "delete from Item where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,item_id);
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
