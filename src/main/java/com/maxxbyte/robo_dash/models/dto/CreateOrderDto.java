package com.maxxbyte.robo_dash.models.dto;

import javax.validation.constraints.NotNull;

public class CreateOrderDto {

    @NotNull
    private Integer deliveryLocationId;

    public CreateOrderDto() {
    }

    public Integer getDeliveryLocationId() {
        return deliveryLocationId;
    }

    public void setDeliveryLocationId(Integer deliveryLocationId) {
        this.deliveryLocationId = deliveryLocationId;
    }
}