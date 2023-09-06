package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.example.model.config.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BingNewsController {
    public static List<News> getAllNews(BingNewsConfig bingNewsConfig, PropertyMapConfig propertyMapConfig) throws Exception {
        List<Channel> allChannel = getAllChannel(bingNewsConfig);
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

    public static NodeList getNodeListFromRssUrl(String rssUrl) throws Exception {
        URL url = new URL(rssUrl);
        try (InputStream inputStream = url.openStream()) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(inputStream);
            doc.getDocumentElement().normalize();
            return doc.getElementsByTagName("item");
        }
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

    public static List<Channel> getAllChannel(BingNewsConfig bingNewsConfig) {
        var categories = bingNewsConfig.getCategories();
        List<Channel> listChannel = categories.stream()
                .flatMap(category -> category.getListChanel().stream())
                .collect(Collectors.toList());
        return listChannel;
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

    public static WeatherInfo getWeatherInfo(WeatherConfig weatherConfig) throws Exception {
        HttpResponse<String> response = getAPIResponse(weatherConfig);
        WeatherInfo weatherInfo = parseWeatherInfo(response.body(), weatherConfig);
        return weatherInfo;
    }

    private static WeatherInfo parseWeatherInfo(String responseBody, WeatherConfig weatherConfig) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);
        WeatherInfo weatherInfo = new WeatherInfo();
        for (var item : weatherConfig.getWeatherMappings()) {
            String value = rootNode.at(item.getTagName()).asText();
            setPropertyValue(weatherInfo, item.getPropertyName(), value);
        }
        List<HourTemperature> hourTemperatures = parseHourTemperatures(weatherConfig, rootNode);
        setPropertyValue(weatherInfo, weatherConfig.getHourTag().getPropertyName(), hourTemperatures);
        return weatherInfo;
    }

    private static List<HourTemperature> parseHourTemperatures(WeatherConfig weatherConfig, JsonNode rootNode) throws Exception {
        List<HourTemperature> hourTemperatures = new ArrayList<>();
        JsonNode forecastNode = rootNode.at(weatherConfig.getForecastTag());
        for (var item: forecastNode) {
            JsonNode hourlyForecast = item.at(weatherConfig.getHourTag().getTag());
            parseHourItem(weatherConfig, hourTemperatures, hourlyForecast);
        }
        return hourTemperatures;
    }

    private static void parseHourItem(WeatherConfig weatherConfig, List<HourTemperature> hourTemperatures, JsonNode hourlyForecast) throws Exception {
        for (JsonNode hourNode : hourlyForecast) {
            HourTemperature hourTemperature = new HourTemperature();
            for (var hourItem : weatherConfig.getHourTag().getHourMappings()) {
                String value = hourNode.at(hourItem.getTagName()).asText();
                setPropertyValue(hourTemperature, hourItem.getPropertyName(), value);
            }
            hourTemperatures.add(hourTemperature);
        }
    }

    public static SportInfo getSportInfo(SportConfig sportConfig) throws Exception {
        HttpResponse<String> response = getAPIResponse(sportConfig);
        SportInfo sportInfo = parseSportInfo(response.body(), sportConfig);
        return sportInfo;
    }

    private static HttpResponse<String> getAPIResponse(APIConfig apiConfig) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiConfig.getURI()))
                .header(apiConfig.getKeyName(), apiConfig.getKeyValue())
                .header(apiConfig.getHostValue(), apiConfig.getHostValue())
                .method(apiConfig.getMethod(), HttpRequest.BodyPublishers.noBody())
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static SportInfo parseSportInfo(String responseBody, SportConfig sportConfig) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);
        rootNode = rootNode.get(sportConfig.getItemTag());
        SportInfo sportInfo = new SportInfo();
        List<Match> listMatch = new ArrayList<>();
        for (JsonNode itemNode : rootNode) {
            for (var sportMapping : sportConfig.getSportMappings()) {
                String value = itemNode.at(sportMapping.getTagName()).asText();
                setPropertyValue(sportInfo, sportMapping.getPropertyName(), value);
                Match match = parseMatch(itemNode, sportConfig);
                listMatch.add(match);
            }
            setPropertyValue(sportInfo, sportConfig.getMatchTag().getPropertyName(), listMatch);
        }
        return sportInfo;
    }

    private static Match parseMatch(JsonNode itemNode, SportConfig sportConfig) throws Exception {
        Match match = new Match();
        for (var matchMapping : sportConfig.getMatchTag().getMatchMappings()) {
            String value = itemNode.at(matchMapping.getTagName()).asText();
            setPropertyValue(match, matchMapping.getPropertyName(), value);
        }
        return match;
    }

    public static List<AdArticle> getAdArticles() {
        return null;
    }

    public static List<FinancialInfo> getFinancialInfo() {
        return null;
    }

    public static List<MicrosoftFeed> getMicrosoftFeed() {
        return null;
    }

    public static List<TopNews> getTopNews(TopNewsConfig topNewsConfig) throws Exception {
        HttpResponse<String> response = getAPIResponse(topNewsConfig);
        List<TopNews> topNewsList = parseTopNews(response.body(), topNewsConfig);
        return topNewsList;
    }

    private static List<TopNews> parseTopNews(String responseBody, TopNewsConfig topNewsConfig) throws Exception {
        List<TopNews> topNewsList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);
        rootNode = rootNode.get(topNewsConfig.getItemTag());
        for (var articleNode : rootNode) {
            TopNews topNews = new TopNews();
            for (var item : topNewsConfig.getTopNewsMappings()) {
                String value = articleNode.at(item.getTagName()).asText();
                setPropertyValue(topNews, item.getPropertyName(), value);
            }
            topNewsList.add(topNews);
        }
        return topNewsList;
    }

    public static List<TrendingNews> getTrendingNews() {
        return null;
    }

}
