package org.example.controller;

import org.example.model.AdArticle;
import org.example.model.CarouselPanel;
import org.example.model.Pagination;
import org.example.model.TrendingNews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarouselService implements Service {

    @Override
    public void readConfig() throws IOException {

    }

    public CarouselPanel getCarousel() throws Exception {
        BingNewsController bingNewsController = new BingNewsController();
        CarouselPanel carouselPanel = new CarouselPanel();
        List<TrendingNews> trendingNews = bingNewsController.getNewsService().getTrendingNews();
        List<AdArticle> adArticleList = bingNewsController.getAdArticleService().getAllAd();
        List<Object> carouselList = new ArrayList<>();
        for (var trendingNew : trendingNews) {
            carouselList.add(trendingNew);
        }
        for (var adArticle : adArticleList) {
            carouselList.add(adArticle);
        }
        carouselPanel.setCarouselList(carouselList);
        carouselPanel.setPagination(new Pagination(carouselList, 1));
        return carouselPanel;
    }
}
