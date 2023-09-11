package org.example.ORM.repository;

import org.example.model.AdArticle;
import java.util.List;

public interface AdRepository {
    List<AdArticle> getAllAd();
    void insertAd(AdArticle adArticle);
    AdArticle findAdByID(String adGuid);
    void deleteAdByID(String adGuid);
    void updateAdByID(AdArticle adArticle, String adGuid);
}
