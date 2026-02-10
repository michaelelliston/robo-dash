package data;

import com.maxxbyte.robo_dash.models.Category;
import com.maxxbyte.robo_dash.models.Location;
import com.maxxbyte.robo_dash.models.LocationType;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDao extends DaoBase{
    public CategoryDao(DataSource dataSource) {super(dataSource);}

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String categoryName = row.getString("category_name");

        return new Category(categoryId,categoryName);
    }
}
