package data;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DaoBase {
    DataSource dataSource;
    public DataSource getDataSource() {
        return dataSource;
    }
    protected Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }

    public DaoBase(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
