package org.example.controller;

import org.example.ORM.repository.AdRepository;

import java.io.IOException;

public class DefaultServiceFactory implements ServiceFactory{
    @Override
    public NewsService createNewsService() throws IOException {
        return new NewsService();
    }

    @Override
    public AdRepository createAdArticleService() throws IOException {
        return new AdArticleService();
    }

    @Override
    public SportInfoService createSportInfoService() throws IOException {
        return new SportInfoService();
    }

    @Override
    public WeatherInfoService createWeatherInfoService() throws IOException {
        return new WeatherInfoService();
    }

    @Override
    public FinancialInfoService createFinancialInfoService() throws IOException {
        return new FinancialInfoService();
    }

    @Override
    public MicrosoftTeamService createMicrosoftTeamService() throws IOException {
        return new MicrosoftTeamService();
    }
}
