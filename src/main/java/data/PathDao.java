package data;

import com.maxxbyte.robo_dash.models.Location;
import com.maxxbyte.robo_dash.models.LocationType;
import com.maxxbyte.robo_dash.models.Path;
import com.maxxbyte.robo_dash.models.PathType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PathDao extends DaoBase{
    public PathDao(DataSource dataSource) {super(dataSource);}

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
