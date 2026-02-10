package data;

import com.maxxbyte.robo_dash.models.Location;
import com.maxxbyte.robo_dash.models.Product;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductDao extends DaoBase {
    public ProductDao(DataSource dataSource) {super(dataSource);}





    private Product mapRow(ResultSet row) throws SQLException
    {
        int productId = row.getInt("product_id");
        int categoryId = row.getInt("category_id");
        String name = row.getString("item_name");
        double price = row.getDouble("price");
        String description = row.getString("description");
        String dietType = row.getString("diet_type");
        int prepTime = row.getInt("prep_time");
        String imageUrl = row.getString("image_url");


        return new Product(name,description,dietType,imageUrl,price,productId,prepTime,categoryId);
    }
}
