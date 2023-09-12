package org.example.controller;

import org.example.ORM.repository.AdRepository;
import org.example.model.config.APIConfig;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BingNewsController {
    private final NewsService newsService;
    private final AdRepository adRepository;
    private final SportInfoService sportInfoService;
    private final WeatherInfoService weatherInfoService;
    private final FinancialInfoService financialInfoService;
    private final MicrosoftTeamService microsoftTeamService;
    private final CarouselService carouselService;

    public BingNewsController() throws IOException {
        ServiceFactory serviceFactory = new ServiceFactory();
        newsService = (NewsService) serviceFactory.createService("NewsService");
        adRepository = (AdRepository) serviceFactory.createService("AdArticleService");
        sportInfoService = (SportInfoService) serviceFactory.createService("SportInfoService");
        weatherInfoService = (WeatherInfoService) serviceFactory.createService("WeatherInfoService");
        financialInfoService = (FinancialInfoService) serviceFactory.createService("FinancialInfoService");
        microsoftTeamService = (MicrosoftTeamService) serviceFactory.createService("MicrosoftTeamService");
        carouselService = (CarouselService) serviceFactory.createService("CarouselService");
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public AdRepository getAdArticleService() {
        return adRepository;
    }

    public SportInfoService getSportInfoService() {
        return sportInfoService;
    }

    public WeatherInfoService getWeatherInfoService() {
        return weatherInfoService;
    }

    public FinancialInfoService getFinancialInfoService() {
        return financialInfoService;
    }

    public MicrosoftTeamService getMicrosoftTeamService() {
        return microsoftTeamService;
    }

    public CarouselService getCarouselService() {
        return carouselService;
    }

    public static NodeList getNodeListFromRssUrl(String rssUrl) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL(rssUrl).openStream());
        return doc.getElementsByTagName("item");
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
}
