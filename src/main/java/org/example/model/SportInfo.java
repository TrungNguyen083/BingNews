package org.example.model;

import java.util.List;

public class SportInfo {
    private String date;
    private List<Match> matches;
    private Pagination pagination;
    public SportInfo() {
    }
    public SportInfo(String date, List<Match> matches, Pagination pagination) {
        this.date = date;
        this.matches = matches;
        this.pagination = pagination;
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

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
