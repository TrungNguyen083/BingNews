package org.example;

import org.example.controller.BingNewsController;
import org.example.controller.ConfigService;
import org.example.model.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String bingNewsConfigPath = "";
        BingNewsConfig bingNewsConfig = ConfigService.readConfig(bingNewsConfigPath, BingNewsConfig.class);
        String mappingConfigPath = "";
        MappingConfig mappingConfig = ConfigService.readConfig(mappingConfigPath, MappingConfig.class);
        String weatherApiUrl = "";
        List<News> newsList = BingNewsController.getAllNews(bingNewsConfig,mappingConfig);
        List<AdArticle> adArticleList = BingNewsController.getAdArticles();
        WeatherInfo weatherInfoList = BingNewsController.getWeatherInfo(weatherApiUrl);
        List<FinancialInfo> financialInfoList = BingNewsController.getFinancialInfo();
        List<SportInfo> sportInfoList = BingNewsController.getSportInfo();
        List<MicrosoftFeed> microsoftFeedList = BingNewsController.getMicrosoftFeed();
        List<TopNews> topNewsList = BingNewsController.getTopNews();
        List<TrendingNews> trendingNewsList = BingNewsController.getTrendingNews();
    }


}