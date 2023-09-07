package org.example.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.HourTemperature;
import org.example.model.WeatherInfo;
import org.example.model.config.WeatherConfig;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class WeatherInfoService {
    WeatherConfig weatherConfig;

    public WeatherInfoService() throws IOException {
        String weatherConfigPath = ".\\src\\main\\resources\\WeatherConfig.json";
        weatherConfig = ConfigService.readConfig(weatherConfigPath, WeatherConfig.class);
    }
    public WeatherInfo getWeatherInfo() throws Exception {
        HttpResponse<String> response = BingNewsController.getAPIResponse(weatherConfig);
        WeatherInfo weatherInfo = parseWeatherInfo(response.body(), weatherConfig);
        return weatherInfo;
    }

    private WeatherInfo parseWeatherInfo(String responseBody, WeatherConfig weatherConfig) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);
        WeatherInfo weatherInfo = new WeatherInfo();
        for (var item : weatherConfig.getWeatherMappings()) {
            String value = rootNode.at(item.getTagName()).asText();
            BingNewsController.setPropertyValue(weatherInfo, item.getPropertyName(), value);
        }
        List<HourTemperature> hourTemperatures = parseHourTemperatures(weatherConfig, rootNode);
        BingNewsController.setPropertyValue(weatherInfo, weatherConfig.getHourTag().getPropertyName(), hourTemperatures);
        return weatherInfo;
    }

    private List<HourTemperature> parseHourTemperatures(WeatherConfig weatherConfig, JsonNode rootNode) throws Exception {
        List<HourTemperature> hourTemperatures = new ArrayList<>();
        JsonNode forecastNode = rootNode.at(weatherConfig.getForecastTag());
        for (var item: forecastNode) {
            JsonNode hourlyForecast = item.at(weatherConfig.getHourTag().getTag());
            parseHourItem(weatherConfig, hourTemperatures, hourlyForecast);
        }
        return hourTemperatures;
    }

    private void parseHourItem(WeatherConfig weatherConfig, List<HourTemperature> hourTemperatures, JsonNode hourlyForecast) throws Exception {
        for (JsonNode hourNode : hourlyForecast) {
            HourTemperature hourTemperature = new HourTemperature();
            for (var hourItem : weatherConfig.getHourTag().getHourMappings()) {
                String value = hourNode.at(hourItem.getTagName()).asText();
                BingNewsController.setPropertyValue(hourTemperature, hourItem.getPropertyName(), value);
            }
            hourTemperatures.add(hourTemperature);
        }
    }
}
