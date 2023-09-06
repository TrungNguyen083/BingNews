package org.example;

import org.example.controller.BingNewsController;
import org.example.controller.ConfigService;
import org.example.model.*;
import org.example.model.config.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String bingNewsConfigPath = "";
        BingNewsConfig bingNewsConfig = ConfigService.readConfig(bingNewsConfigPath, BingNewsConfig.class);
        String mappingConfigPath = "";
        PropertyMapConfig propertyMapConfig = ConfigService.readConfig(mappingConfigPath, PropertyMapConfig.class);
        String weatherConfigPath = ".\\src\\main\\resources\\WeatherConfig.json";
        WeatherConfig weatherConfig = ConfigService.readConfig(weatherConfigPath, WeatherConfig.class);
        List<News> newsList = BingNewsController.getAllNews(bingNewsConfig, propertyMapConfig);
        List<AdArticle> adArticleList = BingNewsController.getAdArticles();
        WeatherInfo weatherInfoList = BingNewsController.getWeatherInfo(weatherConfig);
        List<FinancialInfo> financialInfoList = BingNewsController.getFinancialInfo();
        SportInfo sportInfoList = BingNewsController.getSportInfo(new SportConfig());
        List<MicrosoftFeed> microsoftFeedList = BingNewsController.getMicrosoftFeed();
        List<TopNews> topNewsList = BingNewsController.getTopNews(new TopNewsConfig());
        List<TrendingNews> trendingNewsList = BingNewsController.getTrendingNews();
    }


}