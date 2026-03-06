package com.maxxbyte.robo_dash.models;

public class OrderItem {

    int productId;
    int quantity = 1;


    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
