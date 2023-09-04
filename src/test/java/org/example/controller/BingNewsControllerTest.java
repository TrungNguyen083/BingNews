package org.example.controller;

import junit.framework.TestCase;
import org.example.model.WeatherInfo;
import org.example.model.config.BingNewsConfig;
import org.example.model.config.PropertyMapConfig;
import org.example.model.News;
import org.example.model.config.WeatherConfig;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class BingNewsControllerTest extends TestCase {

    @Test
    public void testGetAllNews() throws Exception {
        String bingNewsConfigPath = ".\\src\\main\\resources\\BingNewsConfig.json";
        BingNewsConfig bingNewsConfig = ConfigService.readConfig(bingNewsConfigPath, BingNewsConfig.class);
        String mappingConfigPath = ".\\src\\main\\resources\\MappingConfig.json";
        PropertyMapConfig propertyMapConfig = ConfigService.readConfig(mappingConfigPath, PropertyMapConfig.class);
        List<News> newsList = BingNewsController.getAllNews(bingNewsConfig, propertyMapConfig);

        assertTrue(newsList.size() > 0);
        assertNotNull(newsList);
    }

    public void testGetAdArticles() {
    }

    public void testGetWeatherInfo() throws Exception {
        String weatherConfigPath = ".\\src\\main\\resources\\WeatherConfig.json";
        WeatherConfig weatherConfig = ConfigService.readConfig(weatherConfigPath, WeatherConfig.class);
        WeatherInfo weatherInfo = BingNewsController.getWeatherInfo(weatherConfig);
        weatherInfo.printInfo();

        assertNotNull(weatherInfo);
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