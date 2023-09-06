package org.example.model;

import java.util.List;

public class League {
    private String leagueName;
    private String leagueLogo;
    public League() {
    }

    public League(String leagueName, String leagueLogo) {
        this.leagueName = leagueName;
        this.leagueLogo = leagueLogo;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getLeagueLogo() {
        return leagueLogo;
    }
}
