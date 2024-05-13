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
        String query = "select * from classifies inner join item inner join keywords";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Classifies classi = new Classifies();
                classi.setName(getKey(rs.getString("name")));
                classi.setIdNasa(getItem(rs.getInt("nasaId")));

                cassList.add(classi);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cassList;
    }

    public boolean save(Classifies classifies) {
        String query = "insert into clssifies " +
                " (name,nasaId)" +
                " values (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, classifies.getName().getName());
            ps.setInt(2, classifies.getIdNasa().getIdNasa());

            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int nasa_id, String key) {
        String query = "delete from classifies where name = "+"'"+key +"'"+" and nasa_id= "+nasa_id;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,key);
            ps.setInt(2,nasa_id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public Item getItem(int idI)
    {
        String query = "select * from item where nasaId ="+ idI;

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            Item item = new Item();
            item.setIdNasa(rs.getInt("nasaId"));
            return item;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Keywords getKey(String key)
    {
        String query = "select * from keywords where name ="+"'"+key+"'";

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
