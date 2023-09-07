package org.example.controller;

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
import java.util.List;

public class BingNewsController {
    private NewsService newsService;
    private SportInfoService sportInfoService;
    private WeatherInfoService weatherInfoService;

    public BingNewsController() throws IOException {
        newsService = new NewsService();
        sportInfoService = new SportInfoService();
        weatherInfoService = new WeatherInfoService();
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public SportInfoService getSportInfoService() {
        return sportInfoService;
    }

    public WeatherInfoService getWeatherInfoService() {
        return weatherInfoService;
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

    public static void setPropertyValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static Object getPropertyValue(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    public static HttpResponse<String> getAPIResponse(APIConfig apiConfig) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiConfig.getURI()))
                .header(apiConfig.getKeyName(), apiConfig.getKeyValue())
                .header(apiConfig.getHostValue(), apiConfig.getHostValue())
                .method(apiConfig.getMethod(), HttpRequest.BodyPublishers.noBody())
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }


    public List<AdArticle> getAdArticles() {
        return null;
    }

    public List<FinancialInfo> getFinancialInfo() {
        return null;
    }

    public List<MicrosoftFeed> getMicrosoftFeed() {
        return null;
    }

    public List<TrendingNews> getTrendingNews() {
        return null;
    }

}
