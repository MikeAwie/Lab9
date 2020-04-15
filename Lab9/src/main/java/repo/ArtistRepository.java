package repo;

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
public class ArtistRepository {

    private final EntityManager em;

    public ArtistRepository(EntityManager em) {
        this.em = em;
    }

    public Artist create(Artist artist) {
        try {
            em.getTransaction().begin();
            em.persist(artist);
            em.getTransaction().commit();
            return artist;
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    public Artist findById(Integer id) {
        return em.find(Artist.class, id);
    }

    public List<Artist> findByName(String name) {
        List<Artist> artists = em.createNamedQuery("Artist.findByName", Artist.class).setParameter("name", name)
                .getResultList();
        return artists;
    }
}
