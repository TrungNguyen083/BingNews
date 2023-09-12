package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class CarouselPanel {
    private List<Object> carouselList;
    Pagination pagination;

    public CarouselPanel() {
        carouselList = new ArrayList<>();
    }

    public CarouselPanel(List<Object> carouselList, Pagination pagination) {
        this.carouselList = carouselList;
        this.pagination = pagination;
    }

    public List<Object> getCarouselList() {
        return carouselList;
    }

    public void setCarouselList(List<Object> carouselList) {
        this.carouselList = carouselList;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
