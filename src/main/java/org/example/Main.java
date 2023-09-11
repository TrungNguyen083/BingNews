package org.example;

import org.example.controller.*;
import org.example.model.*;
import org.example.model.config.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BingNewsController bingNewsController = new BingNewsController();
        List<News> newsList = bingNewsController.getNewsService().getAllNews();
        List<AdArticle> adArticleList = bingNewsController.getAdArticleService().getAllAd();
        WeatherInfo weatherInfoList = bingNewsController.getWeatherInfoService().getWeatherInfo();
        FinancialInfo financialInfoList = bingNewsController.getFinancialInfoService().getFinancialInfo();
        SportInfo sportInfoList = bingNewsController.getSportInfoService().getSportInfo();
        List<MicrosoftFeed> microsoftFeedList = bingNewsController.getMicrosoftTeamService().getMicrosoftFeed();
        List<TopNews> topNewsList = bingNewsController.getNewsService().getTopNews();
        List<TrendingNews> trendingNewsList = bingNewsController.getNewsService().getTrendingNews();
    }


}