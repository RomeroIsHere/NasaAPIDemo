package com.example.nasaapidemo.database.Dao;

import com.example.nasaapidemo.Models.*;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static com.example.nasaapidemo.database.MySQLConnection.getConnection;

public class ClassifiesDao {
    Connection conn = getConnection();


    public Optional<Classifies> findById(int id) {
        return Optional.empty();
    }

    public List<Classifies> findAll() {

        List<Classifies> cassList = FXCollections.observableArrayList();
        String query = "select * from Classifies inner join Item inner join Keywords";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Classifies classi = new Classifies();
                classi.setIdNasa(getItem(rs.getInt("IdNasa")));
                classi.setName(getKey(rs.getString("name")));

                cassList.add(classi);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cassList;
    }

    public boolean save(Classifies classifies) {
        String query = "insert into clssifies " +
                " (idNasa,name)" +
                " values (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, classifies.getIdNasa().getIdNasa());
            ps.setString(2, classifies.getName().getName());

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



    public Item getItem(int idI)
    {
        String query = "select * from Item where idNasa ="+ idI;

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            Item item = new Item();
            item.setIdNasa(rs.getInt("id"));
            return item;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Keywords getKey(String key)
    {
        String query = "select * from MediaType where id ="+"'"+key+"'";

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            Keywords keywords = new Keywords();
            keywords.setName(rs.getString("name"));
            return keywords;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
