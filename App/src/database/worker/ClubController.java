package database.worker;

import database.connection.ConnectionPool;
import model.Club;
import model.Match;

import java.sql.*;
import java.util.ArrayList;

public class ClubController {

    public static ArrayList<Club> getClubs() {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM get_clubs";

        ArrayList<Club> clubs = new ArrayList<>();

        try {

            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                Club club = new Club();

                club.setId(resultSet.getInt(1));
                club.setName(resultSet.getString(2));
                club.setEmblem(resultSet.getBlob(3));
                club.setStadiumName(resultSet.getString(4));
                club.setStadiumCapacity(resultSet.getInt(5));
                club.setCity(resultSet.getString(6));
                club.setCountry(resultSet.getString(7));

                clubs.add(club);
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

        return clubs;
    }

    public static void addNewClub(Club club) {

        Connection connection = null;
        PreparedStatement statement = null;

        addNewStadium(club);

        String query = "INSERT INTO klub (ime, idStadion, idGrad) VALUES (?, ?, ?)";

        try {

            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareStatement(query);

            statement.setString(1, club.getName());
            statement.setInt(2, club.getStadiumId());
            statement.setInt(3, club.getCityId());

            statement.executeUpdate();

        } catch (SQLException ex) {
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
    }

    //
    //
    //

    private static void addNewStadium(Club club) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "INSERT INTO stadion (naziv, kapacitet) VALUES (?, ?)";

        try {

            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, club.getStadiumName());
            statement.setInt(2, club.getStadiumCapacity());

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();

            while (resultSet.next())
                club.setStadiumId(resultSet.getInt(1));

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
}
