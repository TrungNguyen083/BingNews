package org.example.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Match;
import org.example.model.SportInfo;
import org.example.model.config.SportConfig;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class SportInfoService {
    SportConfig sportConfig;
    ConfigService configService;
    public SportInfoService() throws IOException {
        configService = new ConfigService();
        String sportConfigPath = ".\\src\\main\\resources\\SportConfig.json";
        sportConfig = configService.readConfig(sportConfigPath,SportConfig.class);
    }
    public SportInfo getSportInfo() throws Exception {
        HttpResponse<String> response = BingNewsController.getAPIResponse(sportConfig);
        return parseSportInfo(response.body(), sportConfig);
    }


    private SportInfo parseSportInfo(String responseBody, SportConfig sportConfig) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);
        rootNode = rootNode.get(sportConfig.getItemTag());
        SportInfo sportInfo = new SportInfo();
        List<Match> listMatch = new ArrayList<>();
        for (JsonNode itemNode : rootNode) {
            for (var sportMapping : sportConfig.getSportMappings()) {
                String value = itemNode.at(sportMapping.getTagName()).asText();
                BingNewsController.setPropertyValue(sportInfo, sportMapping.getPropertyName(), value);
                Match match = parseMatch(itemNode, sportConfig);
                listMatch.add(match);
            }
            BingNewsController.setPropertyValue(sportInfo, sportConfig.getMatchTag().getPropertyName(), listMatch);
        }
        return sportInfo;
    }

    private Match parseMatch(JsonNode itemNode, SportConfig sportConfig) throws Exception {
        Match match = new Match();
        for (var matchMapping : sportConfig.getMatchTag().getMatchMappings()) {
            String value = itemNode.at(matchMapping.getTagName()).asText();
            BingNewsController.setPropertyValue(match, matchMapping.getPropertyName(), value);
        }
        return match;
    }
}
