package com.maxxbyte.robo_dash.models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order {

    int orderId;
    int userId;
    int deliveryLocationId;
    double totalPrice;
    LocalDateTime orderDate;
    Map<Integer, OrderItem> items = new HashMap<>();
    OrderStatus status;

    public Order() {
        this.status = OrderStatus.IN_PROGRESS;
    }

    public Map<Integer, OrderItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, OrderItem> items) {
        this.items = items;
    }

    public boolean containsOrderItem(int productId) {
        return items.containsKey(productId);
    }

    public void add(OrderItem orderItem) {
        items.put(orderItem.getProductId(), orderItem);
    }

    public double getTotal() {
        double totalPrice = 0;
        for (OrderItem orderItem : items.values()) {
            Product p = orderItem.getProduct();
            totalPrice += p.getPrice();
        }
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeliveryLocationId() {
        return deliveryLocationId;
    }

    public void setDeliveryLocationId(int deliveryLocationId) {this.deliveryLocationId = deliveryLocationId;}

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {}

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {}


}
