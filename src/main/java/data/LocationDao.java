package data;

import com.maxxbyte.robo_dash.configurations.DatabaseConfig;
import com.maxxbyte.robo_dash.models.Location;
import com.maxxbyte.robo_dash.models.LocationType;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class LocationDao extends DaoBase{
    public LocationDao(DataSource dataSource) {
        super(dataSource);
    }

//    public List<Location> getAllLocations() {
//        String sql = "SELECT * FROM location";
//        //try
//        //return;
//    }

//    private Location mapRow(ResultSet row) throws SQLException
//    {
//        int id = row.getInt("location_id");
//        String name = row.getString("building_name");
//        String locationString = row.getString("location_type");
//        LocationType locationType = LocationType.valueOf(locationString);
//        long latitude = row.getLong("latitude");
//        long longitude = row.getLong("longitude");
//
//        return new Location(id,name,locationType,latitude,longitude);
//    }
}
