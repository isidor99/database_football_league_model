package model;

public class Goal {

    private int goalId;
    private int footballerId;
    private int matchId;
    private int clubId;
    private String footballerName;
    private String footballerSurname;
    private String club;
    private boolean ownGoal;
    private int minute;

    public Goal () { }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public int getFootballerId() {
        return footballerId;
    }

    public void setFootballerId(int footballerId) {
        this.footballerId = footballerId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getFootballerName() {
        return footballerName;
    }

    public void setFootballerName(String footballerName) {
        this.footballerName = footballerName;
    }

    public String getFootballerSurname() {
        return footballerSurname;
    }

    public void setFootballerSurname(String footballerSurname) {
        this.footballerSurname = footballerSurname;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public boolean isOwnGoal() {
        return ownGoal;
    }

    public void setOwnGoal(boolean ownGoal) {
        this.ownGoal = ownGoal;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
