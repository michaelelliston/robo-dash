package data;

import com.maxxbyte.robo_dash.configurations.DatabaseConfig;

import javax.sql.DataSource;

public class LocationDao extends DaoBase{
    public LocationDao(DataSource dataSource) {
        super(dataSource);
    }


}
