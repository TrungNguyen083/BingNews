package org.example.controller;

import org.example.model.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BingNewsController {
    public static List<News> getAllNews(BingNewsConfig bingNewsConfig, PropertyMapConfig propertyMapConfig) throws Exception {
        //TODO: Get all rssUrl in file config
        List<Channel> allChannel = getAllChannel(bingNewsConfig);
        //TODO: Read all rssUrl and get NodeList and parse it to List<News>
        List<News> newsList = new ArrayList<>();
        for (var channel : allChannel) {
            NodeList nodeList = getNodeListFromRssUrl(channel.getRSSURL());
            for (int i = 0; i < nodeList.getLength(); i++) {
                Channel channelConfig = channel;
                channelConfig.setPropertyMaps(propertyMapConfig.getChannels()
                        .stream()
                        .filter(x -> x.getChannelName().equals(channel.getChannelName()))
                        .findFirst()
                        .orElse(null)
                        .getPropertyMaps());
                News news = parseNodeItem(nodeList.item(i), channelConfig, News.class);
                newsList.add(news);
            }
        }
        return newsList;
    }

    public static NodeList getNodeListFromRssUrl(String RssUrl) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL(RssUrl).openStream());
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName("item");
    }

    public static <T> T parseNodeItem(Node item, Channel channel, Class<T> classTarget) throws Exception {
        if (item.getNodeType() == Node.ELEMENT_NODE) {
            T instance = classTarget.newInstance();
            Element itemElement = (Element) item;
            for (var propertyMap : channel.getPropertyMaps()) {
                String propertyName = propertyMap.getPropertyName();
                String value = itemElement.getElementsByTagName(propertyMap.getTagName()).item(0).getTextContent();
                setPropertyValue(instance, propertyName, value);

            }
            String imageUrl = getImageValue(item, channel);
            setPropertyValue(instance, "image", imageUrl);
            return instance;
        }
        return null;
    }

    public static String getImageValue(Node item, Channel channel) throws IOException {
        String imageConfigPath = ".\\src\\main\\resources\\ImageConfig.json";
        ImageConfig imageConfig = ConfigService.readConfig(imageConfigPath, ImageConfig.class);
        Channel channelConfig = channel;
        Channel matchingChannel = imageConfig.getChannels()
                .stream()
                .filter(x -> x.getChannelName().equals(channel.getChannelName()))
                .findFirst()
                .orElse(null);

        channelConfig.setImgTagList(matchingChannel != null ? matchingChannel.getImgTagList() : null);
        channelConfig.setImgAttr(matchingChannel != null ? matchingChannel.getImgAttr() : null);
        return mapImageConfig(item, channelConfig);
    }

    private static String mapImageConfig(Node item, Channel channelConfig) {
        for (var tag : channelConfig.getImgTagList()) {
            item = ((Element) item).getElementsByTagName(tag).item(0);
        }

        if (channelConfig.getImgAttr() != null) {
            String imageUrl = ((Element) item).getAttribute(channelConfig.getImgAttr());
            return imageUrl;
        }
        return item.getTextContent();
        // return null;
    }

    public static void setPropertyValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static List<Channel> getAllChannel(BingNewsConfig bingNewsConfig) {
        var categories = bingNewsConfig.getCategories();
        List<Channel> listChannel = categories.stream()
                .flatMap(category -> category.getListChanel().stream())
                .collect(Collectors.toList());
        return listChannel;
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
