package app;

import entity.Album;
import entity.Artist;
import java.util.List;
import javax.persistence.EntityManager;
import repo.AlbumRepository;
import repo.ArtistRepository;
import util.PersistenceUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mike
 */
public class AlbumManager {

    public static void main(String[] args) {
        EntityManager em = PersistenceUtil.createEntityManager();
        ArtistRepository artistRepository = new ArtistRepository(em);
        AlbumRepository albumRepository = new AlbumRepository(em);
        Artist flatbushZombies = new Artist();
        flatbushZombies.setName("Flatbush Zombies");
        flatbushZombies.setCountry("USA");
        artistRepository.create(flatbushZombies);
        System.out.println(flatbushZombies.getName());
        Album album = new Album();
        album.setName("Vacation In Hell");
        album.setReleaseYear(2018);
        album.setArtist(flatbushZombies);
        albumRepository.create(album);
        System.out.println(album.getName());
        List<Artist> artists = artistRepository.findByName("Flatbush Zombies");
        System.out.println(artists.get(0).getName());
        List<Album> albums = albumRepository.findByArtist(flatbushZombies);
        System.out.println(albums.get(0).getName());
        PersistenceUtil.closeEntityManager();
    }

}
