package com.maxxbyte.robo_dash.data;

import com.maxxbyte.robo_dash.models.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PathDao extends DaoBase{
    public PathDao(DataSource dataSource) {super(dataSource);}

    public Path getById(int pathId)
    {
        String sql = "SELECT * FROM paths WHERE path_id = ?";
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pathId);

            ResultSet row = statement.executeQuery();

            if (row.next())
            {
                return mapRow(row);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Path getByDistance(int distance)
    {
        String sql = "SELECT * FROM products WHERE distance_meters = ?";
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, distance);

            ResultSet row = statement.executeQuery();

            if (row.next())
            {
                return mapRow(row);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Path getByPathType(PathType pathType)
    {
        String sql = "SELECT * FROM products WHERE path_type = ?";
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pathType.toString());

            ResultSet row = statement.executeQuery();

            if (row.next())
            {
                return mapRow(row);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }



    private Path mapRow(ResultSet row) throws SQLException
    {
        int pathId = row.getInt("path_id");
        int fromLocationId = row.getInt("from_location_id");
        int toLocationId = row.getInt("to_location_id");
        int distance = row.getInt("distance_meters");
        String pathString = row.getString("path_type");
        PathType pathType = PathType.valueOf(pathString);

        return new Path(pathId,fromLocationId,toLocationId,distance,pathType);
    }
}
