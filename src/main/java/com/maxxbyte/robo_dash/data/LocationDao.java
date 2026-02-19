package com.maxxbyte.robo_dash.data;

import com.maxxbyte.robo_dash.models.Location;
import com.maxxbyte.robo_dash.models.LocationType;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocationDao extends DaoBase {
    public LocationDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<Location> getAllLocations() {
        String sql = "SELECT * FROM delivery_locations;";
        List<Location> locations = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Location location = mapRow(resultSet);
                locations.add(location);
            }
            return locations;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return null;
    }

    public Location getLocationById(int id) {
        String sql = "SELECT * FROM delivery_locations WHERE location_id = ?;";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return null;
    }

    public List<Location> getLocationsByType(LocationType type) {
        String sql = "SELECT * FROM delivery_locations WHERE location_type = ?;";
        List<Location> locations = new ArrayList<>();
        String typeString = String.valueOf(type);

        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, typeString);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Location location = mapRow(resultSet);
                    locations.add(location);
                }
            }
            return locations;

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return null;
    }

    private Location mapRow(ResultSet row) throws SQLException {
        int id = row.getInt("location_id");
        String name = row.getString("building_name");
        String locationString = row.getString("location_type");
        LocationType locationType = LocationType.valueOf(locationString);
        long latitude = row.getLong("latitude");
        long longitude = row.getLong("longitude");

        return new Location(id, name, locationType, latitude, longitude);
    }
}
