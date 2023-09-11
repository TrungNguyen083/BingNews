package org.example.controller;

import java.io.IOException;

public class ServiceFactory {
    public Service createService(String serviceType) throws IOException {
        if (serviceType.equals("NewsService")) {
            return new NewsService();
        } else if (serviceType.equals("AdArticleService")) {
            return new AdArticleService();
        } else if (serviceType.equals("SportInfoService")) {
            return new SportInfoService();
        } else if (serviceType.equals("WeatherInfoService")) {
            return new WeatherInfoService();
        } else if (serviceType.equals("FinancialInfoService")) {
            return new FinancialInfoService();
        } else if (serviceType.equals("MicrosoftTeamService")) {
            return new MicrosoftTeamService();
        } else {
            throw new IOException("Invalid service type");
        }
    }
}

