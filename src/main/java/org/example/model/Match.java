package org.example.model;

public class Match {
    private String leagueName;
    private String leagueLogo;
    private String homeTeamName;
    private String homeTeamLogo;
    private String awayTeamName;
    private String awayTeamLogo;
    private String homeTeamGoal;
    private String awayTeamGoal;

    public Match() {
    }

    public Match(String leagueName, String leagueLogo, String homeTeamName, String homeTeamLogo, String awayTeamName, String awayTeamLogo, String homeTeamGoal, String awayTeamGoal) {
        this.leagueName = leagueName;
        this.leagueLogo = leagueLogo;
        this.homeTeamName = homeTeamName;
        this.homeTeamLogo = homeTeamLogo;
        this.awayTeamName = awayTeamName;
        this.awayTeamLogo = awayTeamLogo;
        this.homeTeamGoal = homeTeamGoal;
        this.awayTeamGoal = awayTeamGoal;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getLeagueLogo() {
        return leagueLogo;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getHomeTeamLogo() {
        return homeTeamLogo;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public String getAwayTeamLogo() {
        return awayTeamLogo;
    }

    public String getHomeTeamGoal() {
        return homeTeamGoal;
    }

    public String getAwayTeamGoal() {
        return awayTeamGoal;
    }

    public void printInfo() {
        System.out.println("    LeagueName: " + leagueName);
        System.out.println("    LeagueLogo: " + leagueLogo);
        System.out.println("    HomeTeamName: " + homeTeamName);
        System.out.println("    HomeTeamLogo: " + homeTeamLogo);
        System.out.println("    AwayTeamName: " + awayTeamName);
        System.out.println("    AwayTeamLogo: " + awayTeamLogo);
        System.out.println("    HomeTeamGoal: " + homeTeamGoal);
        System.out.println("    AwayTeamGoal: " + awayTeamGoal);
        System.out.println("_____________________________________");
    }
}
