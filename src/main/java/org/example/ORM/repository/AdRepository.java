package org.example.ORM.repository;

import org.example.model.AdArticle;
import org.example.model.config.AdArticleConfig;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface AdRepository {
    List<AdArticle> getAllAd(AdArticleConfig adArticleConfig) throws Exception;
    AdArticle getAdById(String adId);
    void insertAd(AdArticle ad, AdArticleConfig adConfig) throws Exception;
    void updateAd(String imgURL, String title, String sourceURL, String adId);
    void deleteAd(String newsId);
    boolean checkAdExist(String guid) throws SQLException;
}
