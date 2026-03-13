package com.maxxbyte.robo_dash.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class CreateOrderDto {

    @Schema(description = "ID of the delivery location for this order", example = "2")
    private int deliveryLocationId;
    @Schema(description = "Items included in the order")
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