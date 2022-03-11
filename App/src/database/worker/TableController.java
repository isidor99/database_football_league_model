package database.worker;

import database.connection.ConnectionPool;
import model.PositionOnTable;
import model.Table;

import java.sql.*;
import java.util.ArrayList;

public class TableController {

    public static ArrayList<PositionOnTable> getPositionsOnTable(int leagueId, int seasonId) {

        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;

        String query = "{call get_table_data(?, ?)}";

        ArrayList<PositionOnTable> positions = new ArrayList<>();

        try {

            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareCall(query);

            statement.setInt(1, leagueId);
            statement.setInt(2, seasonId);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                PositionOnTable position = new PositionOnTable();

                position.setLeagueId(resultSet.getInt(1));
                position.setSeasonId(resultSet.getInt(2));
                position.setClubId(resultSet.getInt(3));
                position.setClubName(resultSet.getString(4));
                position.setGoalsFor(resultSet.getInt(5));
                position.setGoalsAgainst(resultSet.getInt(6));
                position.setNumberOfWins(resultSet.getInt(7));
                position.setNumberOfLosses(resultSet.getInt(8));
                position.setNumberOfDraws(resultSet.getInt(9));
                position.setPoints(resultSet.getInt(10));

                positions.add(position);
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

        return positions;
    }

    public static ArrayList<Table> getTablesForLeague(int leagueId) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;

        String query = "{call get_table_for_league(?)}";

        ArrayList<Table> tables = new ArrayList<>();

        try {

            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareCall(query);

            statement.setInt(1, leagueId);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Table table = new Table();

                table.setLeagueId(resultSet.getInt(1));
                table.setLeagueName(resultSet.getString(2));
                table.setSeasonId(resultSet.getInt(3));
                table.setSeason(resultSet.getString(4));
                table.setCountry(resultSet.getString(5));

                tables.add(table);
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

        return tables;
    }

    public static int addClubOnTable(int leagueId, int seasonId, int clubId) {

        Connection connection = null;
        PreparedStatement statement = null;
        int error = 0;

        String query = "INSERT INTO pozicija_na_tabeli (idLiga, idSezona, idKlub) VALUES (?, ?, ?)";

        try {

            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareStatement(query);

            statement.setInt(1, leagueId);
            statement.setInt(2, seasonId);
            statement.setInt(3, clubId);

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

    public static int removeClubFromTable(int leagueId, int seasonId, int clubId) {

        Connection connection = null;
        PreparedStatement statement = null;
        int error = 0;

        String query = "DELETE FROM pozicija_na_tabeli WHERE idLiga = ? AND idSezona = ? AND idKlub = ?";

        try {

            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareStatement(query);

            statement.setInt(1, leagueId);
            statement.setInt(2, seasonId);
            statement.setInt(3, clubId);

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
}
