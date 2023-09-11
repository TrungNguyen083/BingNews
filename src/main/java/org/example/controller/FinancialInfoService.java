package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.*;
import org.example.model.config.CryptoConfig;
import org.example.model.config.CurrencyConfig;
import org.example.model.config.StockConfig;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class FinancialInfoService implements Service {
    CurrencyConfig currencyConfig;
    CryptoConfig cryptoConfig;
    StockConfig stockConfig;

    public FinancialInfoService() throws IOException {
        readConfig();
    }

    @Override
    public void readConfig() throws IOException {
        ConfigService configService = new ConfigService();
        String cryptoConfigPath = ".\\src\\main\\resources\\CryptoConfig.json";
        String currencyConfigPath = ".\\src\\main\\resources\\CurrencyConfig.json";
        String stockConfigPath = ".\\src\\main\\resources\\StockConfig.json";
        cryptoConfig = configService.readConfig(cryptoConfigPath, CryptoConfig.class);
        currencyConfig = configService.readConfig(currencyConfigPath, CurrencyConfig.class);
        stockConfig = configService.readConfig(stockConfigPath, StockConfig.class);
    }

    public FinancialInfo getFinancialInfo() throws Exception {
        HttpResponse<String> cryptoResponse = BingNewsController.getAPIResponse(cryptoConfig);
        List<Crypto> cryptos = parseCrypto(cryptoResponse.body());
        HttpResponse<String> currencyResponse = BingNewsController.getAPIResponse(currencyConfig);
        List<Currency> currencies = parseCurrency(currencyResponse.body());
        HttpResponse<String> stockResponse = BingNewsController.getAPIResponse(stockConfig);
        List<Stock> stocks = parseStock(stockResponse.body());
        FinancialInfo financialInfo = new FinancialInfo(currencies, cryptos, stocks);
        return financialInfo;
    }

    private List<Stock> parseStock(String responseBody) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);
        List<Stock> stocks = new ArrayList<>();
        for (JsonNode tagNode : rootNode) {
            for (JsonNode itemNode : tagNode) {
                Stock stock = new Stock();
                for (var cryptoMapping : stockConfig.getStockMappings()) {
                    String value = itemNode.at(cryptoMapping.getTagName()).asText();
                    BingNewsController.setPropertyValue(stock, cryptoMapping.getPropertyName(), value);
                }
                stocks.add(stock);
            }
        }
        return stocks;
    }

    private List<Crypto> parseCrypto(String responseBody) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);
        List<Crypto> cryptos = new ArrayList<>();
        for (JsonNode itemNode : rootNode) {
            Crypto crypto = new Crypto();
            for (var cryptoMapping : cryptoConfig.getCryptoMappings()) {
                String value = itemNode.at(cryptoMapping.getTagName()).asText();
                BingNewsController.setPropertyValue(crypto, cryptoMapping.getPropertyName(), value);
            }
            cryptos.add(crypto);
        }
        return cryptos;
    }

    private List<Currency> parseCurrency(String responseBody) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);
        List<Currency> currencies = new ArrayList<>();
        for (JsonNode tagNode : rootNode) {
            for (JsonNode itemNode : tagNode) {
                Currency currency = new Currency();
                for (var cryptoMapping : currencyConfig.getCurrencyMappings()) {
                    String value = itemNode.at(cryptoMapping.getTagName()).asText();
                    BingNewsController.setPropertyValue(currency, cryptoMapping.getPropertyName(), value);
                }
                currencies.add(currency);
            }
        }
        return currencies;
    }
}
