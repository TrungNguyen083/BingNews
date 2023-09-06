package org.example.controller;

import junit.framework.TestCase;
import org.example.model.SportInfo;
import org.example.model.TopNews;
import org.example.model.WeatherInfo;
import org.example.model.config.*;
import org.example.model.News;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

        assertNotNull(weatherInfo);
    }

    public void testGetFinancialInfo() {
    }

    public void testGetSportInfo() throws Exception {
        String sportConfigPath = ".\\src\\main\\resources\\SportConfig.json";
        SportConfig sportConfig = ConfigService.readConfig(sportConfigPath,SportConfig.class);

        SportInfo sportInfo = BingNewsController.getSportInfo(sportConfig);
        sportInfo.printInfo();
        assertNotNull(sportInfo);
    }

    public void testGetMicrosoftFeed() {
    }

    public void testGetTopNews() throws Exception {
        String topNewsConfigPath = ".\\src\\main\\resources\\TopNewsConfig.json";
        TopNewsConfig topNewsConfig = ConfigService.readConfig(topNewsConfigPath, TopNewsConfig.class);

        List<TopNews> topNewsList = BingNewsController.getTopNews(topNewsConfig);

        assertNotNull(topNewsList);
    }

    public void testGetTrendingNews() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Format the current date using the formatter
        String formattedDate = currentDate.format(formatter);

        System.out.println("Formatted Date (dd/MM/yy): " + formattedDate);
    }


}