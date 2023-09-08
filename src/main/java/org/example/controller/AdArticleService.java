package org.example.controller;

import org.example.ORM.repository.AdRepository;
import org.example.ORM.util.ConnectionManager;
import org.example.model.AdArticle;
import org.example.model.config.AdArticleConfig;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdArticleService implements AdRepository, Service{
    AdArticleConfig adConfig;
    ConfigService configService;
    public AdArticleService() throws IOException {
        readConfig();
    }

    @Override
    public void readConfig() throws IOException {
        configService = new ConfigService();
        String adConfigPath = ".\\src\\main\\resources\\AdArticleConfig.json";
        adConfig = configService.readConfig(adConfigPath, AdArticleConfig.class);
    }

    @Override
    public List<AdArticle> getAllAd() throws Exception {
        List<AdArticle> adArticleList = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(adConfig.getSelectAllAdSQL());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            AdArticle adArticle = new AdArticle();
            for (var mapping : adConfig.getAdMappings()) {
                Method method = resultSet.getClass().getMethod(
                        adConfig.getGetMethod() + getPropertyType(mapping.getTagName()),
                        Class.forName(mapping.getTagName()));
                BingNewsController.setPropertyValue(adArticle,
                        mapping.getPropertyName(),
                        method.invoke(resultSet, mapping.getPropertyName()));
            }
            adArticleList.add(adArticle);
        }
        return adArticleList;
    }

    private String getPropertyType(String tagName){
        String[] listTag = tagName.split("\\.");
        return listTag[listTag.length - 1];
    }

    @Override
    public AdArticle getAdById(String adId) {
        return null;
    }

    @Override
    public void insertAd(AdArticle ad) throws Exception {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(adConfig.getInsertAdSQL());
        int i = 1;
        for (var mapping : adConfig.getAdMappings()) {
            Method method = preparedStatement.getClass().getMethod(
                    adConfig.getSetMethod() + getPropertyType(mapping.getTagName()),
                    int.class,
                    Class.forName(mapping.getTagName()));
            method.invoke(preparedStatement, i, BingNewsController.getPropertyValue(ad, mapping.getPropertyName()));
            i++;
        }
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateAd(String imgURL, String title, String sourceURL, String adId) {

    }

    @Override
    public void deleteAd(String newsId) {

    }

    @Override
    public boolean checkAdExist(String guid) throws SQLException {
        return false;
    }
}
