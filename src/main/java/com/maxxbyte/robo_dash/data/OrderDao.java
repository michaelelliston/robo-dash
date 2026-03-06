package com.maxxbyte.robo_dash.data;

import com.maxxbyte.robo_dash.models.*;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            for (OrderItem item : order.getItems().values())
            {
                addOrderItem(connection, order.getOrderId(), item);
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
                Order order = mapRow(row);

                order.setItems(getItemsByOrderId(connection, orderId));

                return order;
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

    private void addOrderItem(Connection connection, int orderId, OrderItem item) throws SQLException
    {
        String sql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, orderId);
        statement.setInt(2, item.getProductId());
        statement.setInt(3, item.getQuantity());

        statement.executeUpdate();
    }

    private Map<Integer, OrderItem> getItemsByOrderId(Connection connection, int orderId) throws SQLException
    {
        Map<Integer, OrderItem> items = new HashMap<>();

        String sql = "SELECT * FROM order_items WHERE order_id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, orderId);

        ResultSet rows = statement.executeQuery();

        while (rows.next())
        {
            OrderItem item = new OrderItem();

            int productId = rows.getInt("product_id");

            item.setProductId(productId);
            item.setQuantity(rows.getInt("quantity"));

            items.put(productId, item);
        }

        return items;
    }
}
