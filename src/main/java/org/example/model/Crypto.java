package org.example.model;

public class Crypto {
    private String guid;
    private String name;
    private String symbol;
    private String logoCoin;
    private String changePercent24Hr;
    private String marketCap;

    public Crypto() {
    }

    public Crypto(String guid, String name, String symbol, String logoCoin, String changePercent24Hr, String marketCap) {
        this.guid = guid;
        this.name = name;
        this.symbol = symbol;
        this.logoCoin = logoCoin;
        this.changePercent24Hr = changePercent24Hr;
        this.marketCap = marketCap;
    }

    public String getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getLogoCoin() {
        return logoCoin;
    }

    public String getChangePercent24Hr() {
        return changePercent24Hr;
    }

    public String getMarketCap() {
        return marketCap;
    }
}
