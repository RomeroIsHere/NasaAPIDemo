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
        String query = "select * from item inner join media_type";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Item item = new Item();
                item.setIdNasa(rs.getInt("nasaId"));
                item.setHref(rs.getString("href"));
                item.setCenter(rs.getString("center"));
                item.setTitle(rs.getString("title"));
                item.setPhotographer(rs.getString("photographer"));
                item.setLocation(rs.getString("location"));
                item.setDateCreation(rs.getString("date_creation"));
                item.setCveMedia(getCveMedia(rs.getInt("cveMedia")));

                itemList.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    public boolean save(Item item) {
        String query = "insert into item " +
                " (nasaId, href, center, title, photographer, location, date_creation, cveMedia)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, item.getIdNasa());
            ps.setString(2, item.getHref());
            ps.setString(3, item.getCenter());
            ps.setString(4, item.getTitle());
            ps.setString(5, item.getPhotographer());
            ps.setString(6, item.getLocation());
            ps.setString(7, item.getDateCreation());
            ps.setInt(8, item.getCveMedia().getCveMedia());

            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int item_id) {
        String query = "delete from item where nasaId = ?";
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
