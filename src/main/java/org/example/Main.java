package org.example;

import org.example.controller.BingNewsController;
import org.example.controller.ConfigService;
import org.example.controller.NewsService;
import org.example.model.*;
import org.example.model.config.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BingNewsController bingNewsController = new BingNewsController();
        List<News> newsList = bingNewsController.getNewsService().getAllNews();
        List<AdArticle> adArticleList = bingNewsController.getAdArticles();
        WeatherInfo weatherInfoList = bingNewsController.getWeatherInfoService().getWeatherInfo();
        List<FinancialInfo> financialInfoList = bingNewsController.getFinancialInfo();
        SportInfo sportInfoList = bingNewsController.getSportInfoService().getSportInfo();
        List<MicrosoftFeed> microsoftFeedList = bingNewsController.getMicrosoftFeed();
        List<TopNews> topNewsList = bingNewsController.getNewsService().getTopNews();
        List<TrendingNews> trendingNewsList = bingNewsController.getTrendingNews();
    }


}