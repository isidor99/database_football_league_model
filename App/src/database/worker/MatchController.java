package database.worker;

import database.connection.ConnectionPool;
import model.Goal;
import model.Match;

import java.sql.*;
import java.util.ArrayList;

public class MatchController {

    public static ArrayList<Match> getMatchesForLeagueInSeason(int leagueId, int seasonId) {

        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;

        String query = "{call get_matches_for_league_in_season(?, ?)}";

        ArrayList<Match> matches = new ArrayList<>();

        try {

            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareCall(query);

            statement.setInt(1, leagueId);
            statement.setInt(2, seasonId);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Match match = new Match();

                match.setId(resultSet.getInt(1));
                match.setLeagueId(resultSet.getInt(2));
                match.setSeasonId(resultSet.getInt(3));
                match.setHomeClubId(resultSet.getInt(4));
                match.setAwayClubId(resultSet.getInt(5));
                match.setDatetime(resultSet.getTimestamp(6));
                match.setMatchDay(resultSet.getInt(7));
                match.setHomeClub(resultSet.getString(8));
                match.setHomeClubGoals(resultSet.getInt(9));
                match.setAwayClub(resultSet.getString(10));
                match.setAwayClubGoals(resultSet.getInt(11));
                match.setLeague(resultSet.getString(12));

                matches.add(match);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            ConnectionPool.getInstance().checkIn(connection);
        }

        return matches;
    }

    public static void getGoalsForMatch(Match match) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;

        String query = "{call get_goals_for_match(?)}";

        ArrayList<Goal> goals = new ArrayList<>();

        try {

            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareCall(query);

            statement.setInt(1, match.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Goal goal = new Goal();

                goal.setGoalId(resultSet.getInt(1));
                goal.setFootballerId(resultSet.getInt(2));
                goal.setMatchId(resultSet.getInt(3));
                goal.setClubId(resultSet.getInt(4));
                goal.setFootballerName(resultSet.getString(5));
                goal.setFootballerSurname(resultSet.getString(6));
                goal.setClub(resultSet.getString(7));
                goal.setOwnGoal(resultSet.getBoolean(8));
                goal.setMinute(resultSet.getInt(9));

                goals.add(goal);
            }

            match.setGoals(goals);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            ConnectionPool.getInstance().checkIn(connection);
        }
    }

    public static int deleteMatch(Match match) {

        Connection connection = null;
        PreparedStatement statement = null;
        int error = 0;

        String query = "DELETE FROM utakmica WHERE idUtakmica = ?";

        try {

            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareStatement(query);

            statement.setInt(1, match.getId());

            statement.executeUpdate();

        } catch (SQLException ex) {
            error = 1;
            ex.printStackTrace();
        } finally {

            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            ConnectionPool.getInstance().checkIn(connection);
        }

        return error;
    }

    public static int addMatch(Match match) {

        Connection connection = null;
        PreparedStatement statement = null;
        int error = 0;

        String query = "INSERT INTO utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) VALUES (?, ?, ?, ?, ?, ?)";

        try {

            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareStatement(query);

            statement.setTimestamp(1, match.getDatetime());
            statement.setInt(2, match.getMatchDay());
            statement.setInt(3, match.getSeasonId());
            statement.setInt(4, match.getLeagueId());
            statement.setInt(5, match.getHomeClubId());
            statement.setInt(6, match.getAwayClubId());

            statement.executeUpdate();

        } catch (SQLException ex) {
            error = 1;
            ex.printStackTrace();
        } finally {

            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            ConnectionPool.getInstance().checkIn(connection);
        }

        return error;
    }

    public static int updateDateTime(int matchId, Timestamp dateTime) {
        Connection connection = null;
        PreparedStatement statement = null;
        int error = 0;

        String query = "UPDATE utakmica SET datumVrijeme = ? WHERE idUtakmica = ?";

        try {

            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareStatement(query);

            statement.setTimestamp(1, dateTime);
            statement.setInt(2, matchId);

            statement.executeUpdate();

        } catch (SQLException ex) {
            error = 1;
            ex.printStackTrace();
        } finally {

            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            ConnectionPool.getInstance().checkIn(connection);
        }

        return error;
    }

    public static int getNumberOfPlayedMatches(int clubId, int leagueId, int seasonId) {

        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;

        String query = "{call get_num_of_played_matches(?, ?, ?)}";

        int numberOfMatches = 0;

        try {

            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareCall(query);

            statement.setInt(1, clubId);
            statement.setInt(2, leagueId);
            statement.setInt(3, seasonId);

            resultSet = statement.executeQuery();

            while (resultSet.next())
                numberOfMatches = resultSet.getInt(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            ConnectionPool.getInstance().checkIn(connection);
        }

        return numberOfMatches;
    }
}
