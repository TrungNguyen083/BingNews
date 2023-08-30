package org.example.controller;

import junit.framework.TestCase;
import org.example.model.BingNewsConfig;
import org.example.model.MappingConfig;
import org.example.model.News;
import org.junit.Test;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BingNewsControllerTest extends TestCase {

    @Test
    public void testGetAllNews() throws Exception {
        String bingNewsConfigPath = ".\\src\\main\\resources\\BingNewsConfig.json";
        BingNewsConfig bingNewsConfig = ConfigService.readConfig(bingNewsConfigPath, BingNewsConfig.class);
        String mappingConfigPath = ".\\src\\main\\resources\\MappingConfig.json";
        MappingConfig mappingConfig = ConfigService.readConfig(mappingConfigPath, MappingConfig.class);
        List<News> newsList = BingNewsController.getAllNews(bingNewsConfig, mappingConfig);

//        for (var news : newsList) {
//            System.out.println(news.getTitle());
//        }
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