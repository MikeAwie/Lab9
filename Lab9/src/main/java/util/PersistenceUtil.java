package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mike
 */
public class PersistenceUtil {

    private static EntityManagerFactory emf;

    private static final ThreadLocal localEntity = new ThreadLocal();

    public static EntityManager createEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("MusicAlbumsPU");
        }
        EntityManager em = emf.createEntityManager();
        localEntity.set(em);
        return em;
    }

    public static EntityManager getEntityManager() {
        if (localEntity.get() == null) {
            createEntityManager();
        }
        return (EntityManager) localEntity.get();
    }

    public static void closeEntityManager() {
        EntityManager em = (EntityManager) localEntity.get();
        if (em != null && em.isOpen()) {
            em.close();
        }
        localEntity.set(null);
    }
}
