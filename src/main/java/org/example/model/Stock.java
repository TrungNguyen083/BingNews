package org.example.model;

public class Stock {
    private String symbol;
    private String name;
    private String price;
    private String priceChangePercent;

    public Stock() {
    }

    public Stock(String symbol, String name, String price, String priceChangePercent) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.priceChangePercent = priceChangePercent;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getPriceChangePercent() {
        return priceChangePercent;
    }
}
