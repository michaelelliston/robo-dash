package com.maxxbyte.robo_dash.data;

import com.maxxbyte.robo_dash.models.Location;
import com.maxxbyte.robo_dash.models.Order;
import com.maxxbyte.robo_dash.models.OrderStatus;
import com.maxxbyte.robo_dash.models.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDao extends DaoBase{
    public OrderDao(DataSource dataSource) {
        super(dataSource);
    }

    public Order create(Order order)
    {
        String sql = "INSERT INTO orders (user_id, location_id, total_price, order_date, order_progress) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getDeliveryLocationId());
            statement.setDouble(3, order.getTotal());
            statement.setTimestamp(4, Timestamp.valueOf(order.getOrderDate()));
            statement.setString(5, order.getStatus().toString());

            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();

            if (keys.next())
            {
                order.setOrderId(keys.getInt(1));
            }

            return order;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
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

    public List<Order> getByUserId(int userId)
    {
        String sql = "SELECT * FROM orders WHERE user_id = ?";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = mapRow(resultSet);
                    orders.add(order);
                }
            }
            return orders;

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return null;
    }

    public List<Order> getByStatus(String statusString)
    {
        String sql = "SELECT * FROM orders WHERE order_progress = ?";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, statusString);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = mapRow(resultSet);
                    orders.add(order);
                }
            }
            return orders;

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return null;
    }

    private Order mapRow(ResultSet row) throws SQLException
    {
        Order order = new Order();

        order.setOrderId(row.getInt("order_id"));
        order.setUserId(row.getInt("user_id"));
        order.setDeliveryLocationId(row.getInt("location_id"));
        order.setTotalPrice(row.getDouble("total_price"));
        order.setOrderDate(row.getTimestamp("order_date").toLocalDateTime());
        order.setStatus(OrderStatus.valueOf(row.getString("order_progress").toUpperCase()));

        return order;
    }

    public void updateOrderStatus(int orderId, OrderStatus status)
    {
        String sql = "UPDATE orders SET status = ? WHERE order_id = ?";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, status.name());
            statement.setInt(2, orderId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
