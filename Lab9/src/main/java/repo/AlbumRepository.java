package repo;

import entity.Album;
import entity.Artist;
import java.util.List;
import javax.persistence.EntityManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mike
 */
public class AlbumRepository {

    private final EntityManager em;

    public AlbumRepository(EntityManager em) {
        this.em = em;
    }

    public Album create(Album album) {
        try {
            em.getTransaction().begin();
            em.persist(album);
            em.getTransaction().commit();
            return album;
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    public Album findById(Integer id) {
        return em.find(Album.class, id);
    }

    public List<Album> findByName(String name) {
        List<Album> albums = em.createNamedQuery("Album.findByName", Album.class).setParameter("name", name)
                .getResultList();
        return albums;
    }

    public List<Album> findByArtist(Artist artist) {
        List<Album> albums = em.createNamedQuery("Album.findByArtist", Album.class).setParameter("artist", artist)
                .getResultList();
        return albums;
    }
}
