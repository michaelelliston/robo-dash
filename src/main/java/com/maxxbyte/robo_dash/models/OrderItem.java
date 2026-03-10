package com.maxxbyte.robo_dash.models;

public class OrderItem {

    Product product;
    int quantity = 1;
    double price;

    public OrderItem() {
        this.product = new Product();
    }


    public int getProductId() {
        return this.product.getProductId();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductId(int productId) {
        this.product.setProductId(productId);
    }

    public Product  getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
