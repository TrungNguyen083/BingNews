package org.example.controller;

import junit.framework.TestCase;
import org.example.model.SportInfo;
import org.example.model.WeatherInfo;
import org.example.model.config.BingNewsConfig;
import org.example.model.config.PropertyMapConfig;
import org.example.model.News;
import org.example.model.config.SportConfig;
import org.example.model.config.WeatherConfig;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

    public void testGetSportInfo() throws Exception {
        String sportConfigPath = ".\\src\\main\\resources\\SportConfig.json";
        SportConfig sportConfig = ConfigService.readConfig(sportConfigPath,SportConfig.class);

        SportInfo sportInfo = BingNewsController.getSportInfo(sportConfig);
        sportInfo.printInfo();

    }

    public void testGetMicrosoftFeed() {
    }

    public void testGetTopNews() {
    }

    public void testGetTrendingNews() {
    }

    @Test
    public void test() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.weatherapi.com/v1/forecast.json?key=f845cd8d15dc447784562628232208&q=Quan%2012,Ho%20Chi%20Minh"))
                .header("X-RapidAPI-Key", "1")
                .header("X-RapidAPI-Host", "1")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

}