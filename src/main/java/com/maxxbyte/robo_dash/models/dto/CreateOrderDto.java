package com.maxxbyte.robo_dash.models.dto;

import java.util.List;

public class CreateOrderDto {

    private int deliveryLocationId;
    private List<CreateOrderItemDto> items;

    public int getDeliveryLocationId() {
        return deliveryLocationId;
    }

    public void setDeliveryLocationId(int deliveryLocationId) {
        this.deliveryLocationId = deliveryLocationId;
    }

    public List<CreateOrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<CreateOrderItemDto> items) {
        this.items = items;
    }
}