package com.maxxbyte.robo_dash.data;

import com.maxxbyte.robo_dash.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDao extends DaoBase{
    public CategoryDao(DataSource dataSource) {super(dataSource);}

    public Category getById(int categoryId)
    {
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);

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

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String categoryName = row.getString("category_name");

        return new Category(categoryId,categoryName);
    }
}
