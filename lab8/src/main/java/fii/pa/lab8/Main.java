/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fii.pa.lab8;

import fii.pa.lab8.DAO.AlbumController;
import fii.pa.lab8.DAO.ArtistController;
import java.sql.SQLException;

/**
 *
 * @author mike
 */
public class Main {

    public static void main(String[] args) {
        ArtistController artists = new ArtistController();
        AlbumController albums = new AlbumController();

        try {
            artists.create("Young Thug", "USA");
            Database.commit();
            Integer artistId = artists.findByName("Young Thug").get(0);
            albums.create("On the rvn", artistId, 2019);
            Database.commit();
            String album = albums.findByArtist(artistId).get(0);
            System.err.println(artistId);
            System.err.println(album);
            Database.closeConnection();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }
    }
}
