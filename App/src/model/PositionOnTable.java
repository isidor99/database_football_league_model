package model;

public class PositionOnTable {

    private int leagueId;
    private int seasonId;
    private int clubId;
    private String clubName;
    private int goalsFor;
    private int goalsAgainst;
    private int numberOfWins;
    private int numberOfLosses;
    private int numberOfDraws;
    private int points;

    public PositionOnTable() { }

    public PositionOnTable(int leagueId, int seasonId, int clubId, String clubName) {
        setLeagueId(leagueId);
        setSeasonId(seasonId);
        setClubId(clubId);
        setClubName(clubName);
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public int getNumberOfLosses() {
        return numberOfLosses;
    }

    public void setNumberOfLosses(int numberOfLosses) {
        this.numberOfLosses = numberOfLosses;
    }

    public int getNumberOfDraws() {
        return numberOfDraws;
    }

    public void setNumberOfDraws(int numberOfDraws) {
        this.numberOfDraws = numberOfDraws;
    }

    public int getPoints() { return points; }

    public void setPoints(int points) { this.points = points; }

    public int getGoalDifference() {
        return getGoalsFor() - getGoalsAgainst();
    }

    @Override
    public String toString() {
        return clubName;
    }
}
