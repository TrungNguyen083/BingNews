package org.example.model;

import java.util.List;

public class FinancialInfo {
    List<Crypto> cryptoList;
    List<Currency> currencyList;
    List<Stock> stockList;

    public FinancialInfo(List<Currency> currencyList, List<Crypto> cryptoList, List<Stock> stockList) {
        this.cryptoList = cryptoList;
        this.currencyList = currencyList;
        this.stockList = stockList;
    }
}
