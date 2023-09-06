package org.example.model;

import java.util.List;

public class SportInfo {
    private String date;
    private List<Match> matches;
    public SportInfo() {
    }
    public SportInfo(String date, List<Match> matches) {
        this.date = date;
        this.matches = matches;
    }

    public String getDate() {
        return date;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void printInfo() {
        System.out.println("SportInfo:");
        System.out.println("Date: " + date);
        System.out.println("Matches:");
        for (Match match : matches) {
            match.printInfo();
        }
    }
}
