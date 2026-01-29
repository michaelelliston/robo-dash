package com.maxxbyte.robo_dash.models;

public class OrderItem {

    Product product = null;
    int quantity = 1;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return this.product.getProductId();
    }
}
