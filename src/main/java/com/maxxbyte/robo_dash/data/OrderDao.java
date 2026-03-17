package com.maxxbyte.robo_dash.data;

import com.maxxbyte.robo_dash.models.*;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

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
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC;";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = mapRow(resultSet);

                    order.setItems(getItemsByOrderId(connection, order.getOrderId()));
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

    public void updateOrderStatus(int orderId, String status)
    {
        String sql = "UPDATE orders SET order_progress = ? WHERE order_id = ?";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, status);
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
        String sql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, orderId);
        statement.setInt(2, item.getProductId());
        statement.setInt(3, item.getQuantity());
        statement.setDouble(4, item.getPrice());

        statement.executeUpdate();
    }

    @Transactional
    public void addItemToOrder(int orderId, OrderItem item)
    {
        String sql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, orderId);
            statement.setInt(2, item.getProductId());
            statement.setInt(3, item.getQuantity());
            statement.setDouble(4, item.getPrice());

            statement.executeUpdate();

            updateOrderTotal(connection, orderId);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void updateOrderItemQuantity(int orderId, int productId, int quantity)
    {
        String sql = "UPDATE order_items SET quantity = ? WHERE order_id = ? AND product_id = ?";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, quantity);
            statement.setInt(2, orderId);
            statement.setInt(3, productId);

            statement.executeUpdate();

            updateOrderTotal(connection, orderId);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void removeItemFromOrder(int orderId, int productId)
    {
        String sql = "DELETE FROM order_items WHERE order_id = ? AND product_id = ?";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, orderId);
            statement.setInt(2, productId);

            statement.executeUpdate();

            updateOrderTotal(connection, orderId);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    private Map<Integer, OrderItem> getItemsByOrderId(Connection connection, int orderId) throws SQLException
    {
        Map<Integer, OrderItem> items = new HashMap<>();

        String sql = "SELECT order_items.product_id, order_items.quantity, products.item_name, products.price, products.description, products.diet_type, products.image_url, products.prep_time, products.category_id " +
                "FROM order_items " +
                "JOIN products ON order_items.product_id = products.product_id " +
                "WHERE order_items.order_id = ?;";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, orderId);

        ResultSet rows = statement.executeQuery();

        while (rows.next())
        {
            OrderItem item = new OrderItem();

            int productId = rows.getInt("product_id");
            double price = rows.getDouble("price");

            item.setProductId(productId);
            item.setQuantity(rows.getInt("quantity"));
            item.setPrice(price);

            Product product = new Product();

            product.setProductId(productId);
            product.setName(rows.getString("item_name"));
            product.setDescription(rows.getString("description"));
            product.setDietType(rows.getString("diet_type"));
            product.setPrice(rows.getDouble("price"));
            product.setImageUrl(rows.getString("image_url"));
            product.setPrepTime(rows.getInt("prep_time"));
            product.setCategoryId(rows.getInt("category_id"));

            item.setProduct(product);

            items.put(productId, item);
        }

        return items;
    }

    private double calculateOrderTotal(Connection connection, int orderId)
    {
        String sql = "SELECT SUM(quantity * price) AS total FROM order_items WHERE order_id = ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);

            ResultSet result = statement.executeQuery();

            if (result.next())
            {
                return result.getDouble("total");
            }

            return 0.0;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void updateOrderTotal(Connection connection, int orderId)
    {
        double newTotal = calculateOrderTotal(connection, orderId);

        String sql = "UPDATE orders SET total_price = ? WHERE order_id = ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setDouble(1, newTotal);
            statement.setInt(2, orderId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
