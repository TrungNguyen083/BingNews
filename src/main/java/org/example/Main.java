package org.example;

import org.example.controller.BingNewsController;
import org.example.model.*;

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
//        int port = 8080; // Choose any available port
//        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
//
//        // Create a context for your /sportInfo endpoint
//        server.createContext("/sportInfo", new SportInfoHandler());
//
//        // Start the server
//        server.start();
//        System.out.println("Server started on port " + port);
    }

}