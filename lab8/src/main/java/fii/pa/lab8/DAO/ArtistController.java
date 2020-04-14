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
public class ArtistController {

    public void create(String name, String country) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into artists (name,country) values(?,?)");
        statement.setString(1, name);
        statement.setString(2, country);
        statement.executeUpdate();
    }

    public ArrayList<Integer> findByName(String name) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement("select id from artists where name like ?");
        statement.setString(1, name);
        ArrayList<Integer> ids = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            ids.add(resultSet.getInt(1));
        }
        return ids;
    }
}
