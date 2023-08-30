package org.example.controller;

import org.example.model.*;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

public class BingNewsController {
    public static List<News> getAllNews(BingNewsConfig bingNewsConfig, MappingConfig mappingConfig) throws Exception {
        //TODO: Get all rssUrl in file config
        List<Channel> allChannel = getAllChannel(bingNewsConfig);
        //TODO: Read all rssUrl and get NodeList and parse it to List<News>
        List<News> parsedNewsList = parseNewsList(allChannel, mappingConfig);
        return parsedNewsList;
    }

    private static List<News> parseNewsList(List<Channel> allChannel, MappingConfig mappingConfig) {
        return null;
    }

    public static NodeList getNodeListFromRssUrl(String RssUrl) throws IOException, ParserConfigurationException, SAXException {
        return null;
    }

    public static News parseNodeItem(Node item, MappingConfig mappingConfig, String channelID) throws Exception {
        return null;
    }

    public static List<Channel> getAllChannel(BingNewsConfig bingNewsConfig) {
        return null;
    }

    public static List<AdArticle> getAdArticles() {
        return null;
    }

    public static WeatherInfo getWeatherInfo(String weatherApiUrl) {
        String apiRequest = readApiRequest(weatherApiUrl);
        WeatherInfo weatherInfo = parseWeatherInfo(apiRequest);
        return weatherInfo;
    }

    private static WeatherInfo parseWeatherInfo(String apiRequest) {
        String locationName = "";
        String localtime = "";
        List<HourTemperature> listHourTemperature = parseWeatherItems(apiRequest);
        return new WeatherInfo(locationName, localtime, listHourTemperature);
    }

    private static List<HourTemperature> parseWeatherItems(String apiRequest) {
        return null;
    }

    private static String readApiRequest(String weatherApiUrl) {
        return null;
    }

    public static List<FinancialInfo> getFinancialInfo() {
        return null;
    }

    public static List<SportInfo> getSportInfo() {
        return null;
    }

    public static List<MicrosoftFeed> getMicrosoftFeed() {
        return null;
    }

    public static List<TopNews> getTopNews() {
        return null;
    }

    public static List<TrendingNews> getTrendingNews() {
        return null;
    }

}
