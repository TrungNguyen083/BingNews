package org.example.controller;

import junit.framework.TestCase;
import org.example.ORM.repository.AdRepository;
import org.example.model.*;
import org.example.model.config.*;
import org.junit.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BingNewsControllerTest extends TestCase {
    ConfigService configService = new ConfigService();
    ServiceFactory defaultFactory = new DefaultServiceFactory();

    @Test
    public void testGetAllNews() throws Exception {
        BingNewsController bingNewsController = new BingNewsController(defaultFactory);

        List<News> newsList = bingNewsController.getNewsService().getAllNews();

        assertTrue(newsList.size() > 0);
        assertNotNull(newsList);
    }

    public void testGetAdArticles() {
    }

    public void testGetWeatherInfo() throws Exception {
        BingNewsController bingNewsController = new BingNewsController(defaultFactory);

        WeatherInfo weatherInfo = bingNewsController.getWeatherInfoService().getWeatherInfo();
        weatherInfo.printInfo();

        assertNotNull(weatherInfo);
    }

    public void testGetFinancialInfo() {
    }

    public void testGetSportInfo() throws Exception {
        BingNewsController bingNewsController = new BingNewsController(defaultFactory);

        SportInfo sportInfo = bingNewsController.getSportInfoService().getSportInfo();

        assertNotNull(sportInfo);
    }

    public void testGetMicrosoftFeed() {
    }

    public void testGetTopNews() throws Exception {
        BingNewsController bingNewsController = new BingNewsController(defaultFactory);

        List<TopNews> topNewsList = bingNewsController.getNewsService().getTopNews();

        assertNotNull(topNewsList);
    }

    public void testGetTrendingNews() {
        LocalDate yesterday = (LocalDate.now()).minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Format the current date using the formatter
        String formattedDate = yesterday.format(formatter);

        System.out.println("Formatted Date (dd/MM/yy): " + formattedDate);
    }

    @Test
    public void testGetAllAdArticle() throws Exception {
        BingNewsController bingNewsController = new BingNewsController(defaultFactory);
        List<AdArticle> adArticleList = bingNewsController.getAdArticleService().getAllAd();
        for (var item : adArticleList) {
            item.printInfo();
        }
        assertNotNull(adArticleList);
    }

    @Test
    public void testInsertAdArticle() throws Exception {
        String adConfigPath = ".\\src\\main\\resources\\AdArticleConfig.json";
        AdArticleConfig adConfig = configService.readConfig(adConfigPath, AdArticleConfig.class);
        AdRepository adRepository = new AdArticleService();
        AdArticle adArticle = new AdArticle("test", "test", "test", "test", "test");

        adRepository.insertAd(adArticle);
    }


}