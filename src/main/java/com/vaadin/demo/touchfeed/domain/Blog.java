package com.vaadin.demo.touchfeed.domain;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Transient;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Entity
public class Blog {
    @Id
    @GeneratedValue
    private Long id;
    private String url;

    @Transient
    private SyndFeed feed;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public SyndFeed getFeed() {
        if (feed == null) {
            try {
                SyndFeedInput input = new SyndFeedInput();
                feed = input.build(new XmlReader(new URL(url)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return feed;
    }

    public void store() {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    @Transient
    public List<SyndEntry> getEntries() {
        return getFeed().getEntries();
    }

    public static List<Blog> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createQuery("select b from Blog b");
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
        return new ArrayList<Blog>();
    }
}
