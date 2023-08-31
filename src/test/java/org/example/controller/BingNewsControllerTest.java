package org.example.controller;

import junit.framework.TestCase;
import org.example.model.BingNewsConfig;
import org.example.model.PropertyMapConfig;
import org.example.model.News;
import org.junit.Test;

import java.util.List;

public class BingNewsControllerTest extends TestCase {

    @Test
    public void testGetAllNews() throws Exception {
        String bingNewsConfigPath = ".\\src\\main\\resources\\BingNewsConfig.json";
        BingNewsConfig bingNewsConfig = ConfigService.readConfig(bingNewsConfigPath, BingNewsConfig.class);
        String mappingConfigPath = ".\\src\\main\\resources\\MappingConfig.json";
        PropertyMapConfig propertyMapConfig = ConfigService.readConfig(mappingConfigPath, PropertyMapConfig.class);
        List<News> newsList = BingNewsController.getAllNews(bingNewsConfig, propertyMapConfig);

        for (var news : newsList) {
            news.printOutInfo();
        }
    }

    public void testGetAdArticles() {
    }

    public void testGetWeatherInfo() {
    }

    public void testGetFinancialInfo() {
    }

    public void testGetSportInfo() {
    }

    public void testGetMicrosoftFeed() {
    }

    public void testGetTopNews() {
    }

    public void testGetTrendingNews() {
    }
}