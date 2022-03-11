package database.worker;

import database.connection.ConnectionPool;
import model.League;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LeagueController {

    public static ArrayList<League> getLeagues() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM get_leagues";

        ArrayList<League> leagues = new ArrayList<>();

        try {

            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                League league = new League();

                league.setId(resultSet.getInt(1));
                league.setLeagueName(resultSet.getString(2));
                league.setCountry(resultSet.getString(3));

                leagues.add(league);
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

        return leagues;
    }
}
