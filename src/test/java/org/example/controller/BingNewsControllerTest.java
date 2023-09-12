package org.example.controller;

import junit.framework.TestCase;
import org.example.model.*;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class BingNewsControllerTest extends TestCase {

    @Test
    public void testGetAllNews() throws Exception {
        BingNewsController bingNewsController = new BingNewsController();

        List<News> newsList = bingNewsController.getNewsService().getAllNews();

        assertTrue(newsList.size() > 0);
        assertNotNull(newsList);
    }

    public void testGetWeatherInfo() throws Exception {
        BingNewsController bingNewsController = new BingNewsController();

        WeatherInfo weatherInfo = bingNewsController.getWeatherInfoService().getWeatherInfo();

        weatherInfo.printInfo();

        assertNotNull(weatherInfo);
    }

    public void testGetSportInfo() throws Exception {
        BingNewsController bingNewsController = new BingNewsController();

        SportInfo sportInfo = bingNewsController.getSportInfoService().getSportInfo();

        assertNotNull(sportInfo);
    }

    public void testGetMicrosoftFeed() {
    }

    public void testGetTopNews() throws Exception {
        BingNewsController bingNewsController = new BingNewsController();

        List<TopNews> topNewsList = bingNewsController.getNewsService().getTopNews();

        assertNotNull(topNewsList);
    }

    @Test
    public void testGetAllAdArticle() throws Exception {
        BingNewsController bingNewsController = new BingNewsController();
        List<AdArticle> adArticleList = bingNewsController.getAdArticleService().getAllAd();

        for (var adArticle : adArticleList) {
            adArticle.printInfo();
        }
        assertNotNull(adArticleList);
    }

    @Test
    public void testInsertAdArticle() throws IOException {
        AdArticle adArticle = new AdArticle(UUID.randomUUID().toString(), "Nguyen", "Test", "Test", "Test");

        BingNewsController bingNewsController = new BingNewsController();
        bingNewsController.getAdArticleService().insertAd(adArticle);
    }

    @Test
    public void testFindAdArticleByID() throws IOException {
        BingNewsController bingNewsController = new BingNewsController();
        AdArticle adArticle = bingNewsController.getAdArticleService().findAdByID("21355621548461231");
        adArticle.printInfo();
    }

    @Test
    public void testDeleteAdArticleByID() throws IOException {
        BingNewsController bingNewsController = new BingNewsController();
        bingNewsController.getAdArticleService().deleteAdByID("acef138d-0ea1-4c65-8fa3-ce6bcdc768b7");
    }

    public void testUpdateAdArticleByID() throws IOException {
        AdArticle adArticle = new AdArticle("Nguyen", "Nguyen", "Test", "Test");
        BingNewsController bingNewsController = new BingNewsController();
        bingNewsController.getAdArticleService().updateAdByID(adArticle,"0dcb1e40-bdba-41b2-8f31-da92cc65e74a");
    }

    @Test
    public void testGetFinanceInfo() throws Exception {
        BingNewsController bingNewsController = new BingNewsController();
        FinancialInfo financialInfo = bingNewsController.getFinancialInfoService().getFinancialInfo();

        assertNotNull(financialInfo);
    }

    public void testGetSportInfoWithPaging() throws Exception {
        BingNewsController bingNewsController = new BingNewsController();
        SportInfo sportInfo = bingNewsController.getSportInfoService().getSportInfo();

        List<Match> matchList = sportInfo.getPagination().getCurrentPageItems();

        assertTrue(matchList.size() == 3);
    }

    @Test
    public void testGetWeatherInfoWithPaging() throws Exception {
        BingNewsController bingNewsController = new BingNewsController();
        WeatherInfo weatherInfo = bingNewsController.getWeatherInfoService().getWeatherInfo();

        List<HourTemperature> hourTemperatures = weatherInfo.getPagination().getCurrentPageItems();
        weatherInfo.getPagination().nextPage();
        weatherInfo.getPagination().nextPage();
        hourTemperatures = weatherInfo.getPagination().getCurrentPageItems();
        assertTrue(hourTemperatures.size() == 5);
        weatherInfo.getPagination().nextPage();
        weatherInfo.getPagination().nextPage();
        hourTemperatures = weatherInfo.getPagination().getCurrentPageItems();
        assertTrue(hourTemperatures.size() == 4);
    }
}