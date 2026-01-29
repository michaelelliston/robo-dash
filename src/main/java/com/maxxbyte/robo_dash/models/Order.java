package com.maxxbyte.robo_dash.models;

import java.util.HashMap;
import java.util.Map;

public class Order {

    Map<Integer, OrderItem> items = new HashMap<>();

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
}
