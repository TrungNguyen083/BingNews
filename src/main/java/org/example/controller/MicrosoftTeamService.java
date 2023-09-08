package org.example.controller;

import org.example.model.MicrosoftFeed;

import java.io.IOException;
import java.util.List;

public class MicrosoftTeamService implements Service {
    public MicrosoftTeamService() throws IOException {
        readConfig();
    }

    @Override
    public void readConfig() throws IOException {

    }

    public List<MicrosoftFeed> getMicrosoftFeed() {
        return null;
    }
}
