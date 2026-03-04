package com.maxxbyte.robo_dash.data;

import com.maxxbyte.robo_dash.models.Order;
import com.maxxbyte.robo_dash.models.Profile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderDao extends DaoBase{
    public OrderDao(DataSource dataSource) {
        super(dataSource);
    }

    public Order getById(int orderId)
    {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);

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

    private Order mapRow(ResultSet row) throws SQLException
    {
        int orderId = row.getInt("order_id");
        int userId = row.getInt("user_id");
        LocalDateTime orderDate = row.getTimestamp("order_date").toLocalDateTime();
        int locationId = row.getInt("location_id");
        double totalPrice = row.getInt("total_price");

        Order order = new Order();
        order.setOrderId(orderId);
        order.setUserId(userId);
        order.setOrderDate(orderDate);
        order.setDeliveryLocationId(locationId);
        order.setTotalPrice(totalPrice);
        return order;

    }
}
