package com.maxxbyte.robo_dash.data;

import com.maxxbyte.robo_dash.models.Product;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductDao extends DaoBase {
    public ProductDao(DataSource dataSource) {super(dataSource);}

    public Product getById(int productId)
    {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);

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
