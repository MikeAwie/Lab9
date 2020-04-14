/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fii.pa.lab8.DAO;

import fii.pa.lab8.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mike
 */
public class AlbumController {

    public void create(String name, int artistId, int releaseYear) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into albums (name,artist_id,release_year) values(?,?,?)");
        statement.setString(1, name);
        statement.setInt(2, artistId);
        statement.setInt(3, releaseYear);
        statement.executeUpdate();
    }

    public ArrayList<String> findByArtist(int artistId) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement("select name from albums where artist_id=?");
        statement.setInt(1, artistId);
        ArrayList<String> albums = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            albums.add(resultSet.getString(1));
        }
        return albums;
    }
}
