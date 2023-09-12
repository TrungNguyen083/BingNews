package org.example.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.News;
import org.example.model.TopNews;
import org.example.model.TrendingNews;
import org.example.model.config.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewsService implements Service{
    BingNewsConfig bingNewsConfig;
    PropertyMapConfig propertyMapConfig;
    TopNewsConfig topNewsConfig;
    ConfigService configService;
    TrendingNewsConfig trendNewsConfig;

    public NewsService() throws IOException {
        readConfig();
    }

    @Override
    public void readConfig() throws IOException{
        configService = new ConfigService();
        String bingNewsConfigPath = ".\\src\\main\\resources\\BingNewsConfig.json";
        bingNewsConfig = configService.readConfig(bingNewsConfigPath, BingNewsConfig.class);
        String mappingConfigPath = ".\\src\\main\\resources\\MappingConfig.json";
        propertyMapConfig = configService.readConfig(mappingConfigPath, PropertyMapConfig.class);
        String topNewsConfigPath = ".\\src\\main\\resources\\TopNewsConfig.json";
        topNewsConfig = configService.readConfig(topNewsConfigPath, TopNewsConfig.class);
        String trendNewsConfigPath = ".\\src\\main\\resources\\TrendingNewsConfig.json";
        trendNewsConfig = configService.readConfig(trendNewsConfigPath, TrendingNewsConfig.class);
    }

    public List<News> getAllNews() throws Exception {
        List<Channel> allChannel = getAllChannel(bingNewsConfig);
        List<News> newsList = new ArrayList<>();

        for (var channel : allChannel) {
            NodeList nodeList = BingNewsController.getNodeListFromRssUrl(channel.getRSSURL());
            for (int i = 0; i < nodeList.getLength(); i++) {
                channel.setPropertyMaps(propertyMapConfig.getChannels()
                        .stream()
                        .filter(x -> x.getChannelName().equals(channel.getChannelName()))
                        .findFirst()
                        .orElse(null)
                        .getPropertyMaps());
                News news = parseNodeItem(nodeList.item(i), channel, News.class);
                newsList.add(news);
            }
        }
        return newsList;
    }

    public List<TopNews> getTopNews() throws Exception {
        HttpResponse<String> response = BingNewsController.getAPIResponse(topNewsConfig);
        return parseTopNews(response.body(), topNewsConfig);
    }

    private String getImageValue(Node item, Channel channel) throws IOException {
        String imageConfigPath = ".\\src\\main\\resources\\ImageConfig.json";
        ImageConfig imageConfig = configService.readConfig(imageConfigPath, ImageConfig.class);
        Channel matchingChannel = imageConfig.getChannels()
                .stream()
                .filter(x -> x.getChannelName().equals(channel.getChannelName()))
                .findFirst()
                .orElse(null);

        channel.setImgTagList(matchingChannel != null ? matchingChannel.getImgTagList() : null);
        channel.setImgAttr(matchingChannel != null ? matchingChannel.getImgAttr() : null);
        return mapImageConfig(item, channel);
    }

    private  <T> T parseNodeItem(Node item, Channel channel, Class<T> classTarget) throws Exception {
        if (item.getNodeType() == Node.ELEMENT_NODE) {
            T instance = classTarget.newInstance();
            Element itemElement = (Element) item;
            for (var propertyMap : channel.getPropertyMaps()) {
                String propertyName = propertyMap.getPropertyName();
                String value = itemElement.getElementsByTagName(propertyMap.getTagName()).item(0).getTextContent();
                BingNewsController.setPropertyValue(instance, propertyName, value);

            }
            String imageUrl = getImageValue(item, channel);
            BingNewsController.setPropertyValue(instance, "image", imageUrl);
            return instance;
        }
        return null;
    }

    private List<Channel> getAllChannel(BingNewsConfig bingNewsConfig) {
        var categories = bingNewsConfig.getCategories();
        return categories.stream()
                .flatMap(category -> category.getListChanel().stream())
                .collect(Collectors.toList());
    }

    private String mapImageConfig(Node item, Channel channelConfig) {
        for (var tag : channelConfig.getImgTagList()) {
            item = ((Element) item).getElementsByTagName(tag).item(0);
        }
        if (item == null) return null;

        if (channelConfig.getImgAttr() != null) {
            return ((Element) item).getAttribute(channelConfig.getImgAttr());
        }
        return item.getTextContent();
    }

    private List<TopNews> parseTopNews(String responseBody, TopNewsConfig topNewsConfig) throws Exception {
        List<TopNews> topNewsList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);
        rootNode = rootNode.get(topNewsConfig.getItemTag());
        for (var articleNode : rootNode) {
            TopNews topNews = new TopNews();
            for (var item : topNewsConfig.getTopNewsMappings()) {
                String value = articleNode.at(item.getTagName()).asText();
                BingNewsController.setPropertyValue(topNews, item.getPropertyName(), value);
            }
            topNewsList.add(topNews);
        }
        return topNewsList;
    }

    public List<TrendingNews> getTrendingNews() throws Exception {
        List<Channel> allChannel = trendNewsConfig.getListChanel();
        List<TrendingNews> trendingNews = new ArrayList<>();

        for (var channel : allChannel) {
            NodeList nodeList = BingNewsController.getNodeListFromRssUrl(channel.getRSSURL());
            for (int i = 0; i < nodeList.getLength(); i++) {
                TrendingNews news = parseNodeItem(nodeList.item(i), channel, TrendingNews.class);
                trendingNews.add(news);
            }
        }
        return trendingNews;
    }
}
