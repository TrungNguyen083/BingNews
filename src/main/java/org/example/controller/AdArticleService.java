package org.example.controller;

import org.example.ORM.TNframwork.EntityManager;
import org.example.ORM.repository.AdRepository;
import org.example.model.AdArticle;

import java.io.IOException;
import java.util.List;

public class AdArticleService implements AdRepository, Service {

    public AdArticleService() throws IOException {
        readConfig();
    }

    @Override
    public void readConfig() throws IOException {

    }

    @Override
    public List<AdArticle> getAllAd() {
        // Assuming you have an instance of EntityManager and a class representing the entity
        EntityManager entityManager = new EntityManager();
        List<AdArticle> adArticleList = entityManager.getAllItems(AdArticle.class);
        return adArticleList;
    }

    @Override
    public void insertAd(AdArticle adArticle) {
        EntityManager entityManager = new EntityManager();
        entityManager.insertItem(adArticle);
    }

    @Override
    public AdArticle findAdByID(String adGuid) {
        EntityManager entityManager = new EntityManager();
        return entityManager.findById(AdArticle.class, adGuid);
    }

    @Override
    public void deleteAdByID(String adGuid) {
        EntityManager entityManager = new EntityManager();
        entityManager.deleteItem(AdArticle.class, adGuid);
    }

    @Override
    public void updateAdByID(AdArticle adArticle, String adGuid) {
        EntityManager entityManager = new EntityManager();
        entityManager.updateItem(adArticle, adGuid);
    }

}
