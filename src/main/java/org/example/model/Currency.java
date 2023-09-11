package org.example.model;

public class Currency {
    private String fromCurrency;
    private String toCurrency;
    private String shortName;
    private String price;
    private String priceChangePercent;

    public Currency() {
    }

    public Currency(String fromCurrency, String toCurrency, String shortName, String price, String priceChangePercent) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.shortName = shortName;
        this.price = price;
        this.priceChangePercent = priceChangePercent;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public String getShortName() {
        return shortName;
    }

    public String getPrice() {
        return price;
    }

    public String getPriceChangePercent() {
        return priceChangePercent;
    }
}
