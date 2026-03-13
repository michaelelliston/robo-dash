package com.maxxbyte.robo_dash.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateOrderItemDto {

    @Schema(description = "Product ID being ordered", example = "1005")
    private int productId;
    @Schema(description = "Quantity of the product", example = "2")
    private int quantity;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}