package database.worker;

import database.connection.ConnectionPool;
import model.City;
import model.Club;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CityController {

    public static ArrayList<City> getCities() {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM get_cities";

        ArrayList<City> cities = new ArrayList<>();

        try {

            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                City city = new City();

                city.setCityId(resultSet.getInt(1));
                city.setCityName(resultSet.getString(2));
                city.setCountryId(resultSet.getInt(3));
                city.setCountryName(resultSet.getString(4));

                cities.add(city);
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

        return cities;
    }
}
