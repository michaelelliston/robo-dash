package com.maxxbyte.robo_dash.services;

import com.maxxbyte.robo_dash.data.OrderDao;
import com.maxxbyte.robo_dash.data.ProductDao;
import com.maxxbyte.robo_dash.models.*;
import com.maxxbyte.robo_dash.models.dto.CreateOrderDto;
import com.maxxbyte.robo_dash.models.dto.CreateOrderItemDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final Robot robot;

    public OrderService(OrderDao orderDao, ProductDao productDao, Robot robot) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.robot = robot;
    }

    public Order createOrder(int userId, CreateOrderDto dto) {

        Order order = new Order();
        order.setUserId(userId);
        order.setDeliveryLocationId(dto.getDeliveryLocationId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.IN_PROGRESS);

        Map<Integer, OrderItem> items = new HashMap<>();
        double total = 0;

        for (CreateOrderItemDto itemDto : dto.getItems()) {

            Product product = productDao.getById(itemDto.getProductId());

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());

            items.put(product.getProductId(), item);

            total += product.getPrice() * itemDto.getQuantity();
        }

        order.setItems(items);
        order.setTotalPrice(total);

        Order savedOrder = orderDao.create(order);

        // assign order to robot
        robot.setCurrentOrder(savedOrder);
        robot.assignRoute(savedOrder.getDeliveryLocationId());

        return savedOrder;
    }

    public Order getOrder(int orderId) {
        return orderDao.getById(orderId);
    }

    public List<Order> getUserOrders(int userId) {
        return orderDao.getByUserId(userId);
    }

    public void updateStatus(int orderId, String status) {
        orderDao.updateOrderStatus(orderId, status);
    }
}