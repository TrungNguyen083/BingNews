package org.example.controller;

import org.example.ORM.repository.AdRepository;

import java.io.IOException;

public interface ServiceFactory {
    NewsService createNewsService() throws IOException;
    AdRepository createAdArticleService() throws IOException;
    SportInfoService createSportInfoService() throws IOException;
    WeatherInfoService createWeatherInfoService() throws IOException;
    FinancialInfoService createFinancialInfoService() throws IOException;
    MicrosoftTeamService createMicrosoftTeamService() throws IOException;
}

