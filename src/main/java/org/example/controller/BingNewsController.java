package org.example.controller;

import org.example.model.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.example.model.config.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
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
        if (item == null) return null;

        if (channelConfig.getImgAttr() != null) {
            String imageUrl = ((Element) item).getAttribute(channelConfig.getImgAttr());
            return imageUrl;
        }
        return item.getTextContent();
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

    public static WeatherInfo getWeatherInfo(WeatherConfig weatherConfig) throws Exception {
        WeatherChannel weatherChannel = weatherConfig.getWeatherChannels().get(0);
        String apiRequest = readApiRequest(weatherChannel.getAPIUrl());
        WeatherInfo weatherInfo = parseWeatherInfo(apiRequest, weatherChannel);
        return weatherInfo;
    }

    public static String readApiRequest(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String result = "";
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            result = response.toString();
        }
        return result;
    }

    private static WeatherInfo parseWeatherInfo(String apiRequest, WeatherChannel weatherChannel) throws Exception {
        WeatherInfo weatherInfo = new WeatherInfo();
        for (var item : weatherChannel.getMappings()) {
            String value = extractApiValue(apiRequest, item.getStartTag(), item.getEndTag());
            setPropertyValue(weatherInfo, item.getPropertyName(), value);
        }
        List<HourTemperature> listHourTemperature = parseHourTemperatures(weatherChannel);
        setPropertyValue(weatherInfo, weatherChannel.getHourTag().getPropertyName(), listHourTemperature);
        return weatherInfo;
    }

    private static List<HourTemperature> parseHourTemperatures(WeatherChannel weatherChannel) throws Exception {
        String apiHoursUrl;
        List<HourTemperature> listHourTemperature = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            HourTemperature hourTemperature = new HourTemperature();
            apiHoursUrl = weatherChannel.getAPIUrl() + weatherChannel.getHourTag().getExtendURL() + i;
            String apiHours = readApiRequest(apiHoursUrl);
            String hourTag = extractApiValue(apiHours, weatherChannel.getHourTag().getStartTag(), weatherChannel.getHourTag().getEndTag());
            for (var item: weatherChannel.getHourTag().getListHourTemperature()) {
                String value = extractApiValue(hourTag, item.getStartTag(), item.getEndTag());
                setPropertyValue(hourTemperature, item.getPropertyName(), value);
            }
            listHourTemperature.add(hourTemperature);
        }
        return listHourTemperature;
    }

    private static String extractApiValue(String json, String startTag, String endTag) {
        int startIndex = json.indexOf(startTag);
        int endIndex = json.indexOf(endTag, startIndex + startTag.length());
        if (startIndex != -1 && endIndex != -1) {
            return json.substring(startIndex + startTag.length(), endIndex);
        }
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
