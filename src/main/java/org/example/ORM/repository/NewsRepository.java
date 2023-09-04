package org.example.ORM.repository;


import org.example.model.News;

import java.sql.SQLException;
import java.util.List;

public interface NewsRepository {
    List<News> getAllNews();
    News getNewsById(String news);
    void insertNews(News news);
    void updateNews(String imgURL, String title, String sourceURL, String newsId);
    void deleteNews(String newsId);
    boolean checkNewsExist(String guid) throws SQLException;
}
