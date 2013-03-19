package com.vaadin.demo.touchfeed.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Holds an entity manager factory and knows how to create entity manager
 * instances from this.
 */
public class JPAUtil {

    private static final EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("touchfeed");

    /**
     * Creates and returns a new EntityManager
     * 
     * @return a new EntityManager
     */
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
