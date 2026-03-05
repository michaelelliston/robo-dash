package com.maxxbyte.robo_dash.data;

import com.maxxbyte.robo_dash.models.Location;
import com.maxxbyte.robo_dash.models.Order;
import com.maxxbyte.robo_dash.models.OrderStatus;
import com.maxxbyte.robo_dash.models.Profile;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends DaoBase{
    public OrderDao(DataSource dataSource) {
        super(dataSource);
    }

    public Order create(Order order)
    {
        String sql = "INSERT INTO orders (order_id, user_id, order_date, location_id, total_price, order_progress) " +
                " VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection connection = getConnection())
        {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getOrderId());
            ps.setInt(2, order.getUserId());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(4, order.getDeliveryLocationId());
            ps.setDouble(5, order.getTotal());
            ps.setString(6, String.valueOf(order.getStatus()));

            ps.executeUpdate();

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
        int orderId = row.getInt("order_id");
        int userId = row.getInt("user_id");
        LocalDateTime orderDate = row.getTimestamp("order_date").toLocalDateTime();
        int locationId = row.getInt("location_id");
        double totalPrice = row.getDouble("total_price");
        OrderStatus orderStatus = OrderStatus.valueOf(row.getString("order_progress").toUpperCase());

        Order order = new Order();
        order.setOrderId(orderId);
        order.setUserId(userId);
        order.setOrderDate(orderDate);
        order.setDeliveryLocationId(locationId);
        order.setTotalPrice(totalPrice);
        order.setStatus(orderStatus);
        return order;

    }
}
