package com.maxxbyte.robo_dash.models;

public class Product {

    String name;
    String description;
    String dietType;
    double price;
    int productId;
    int prepTime;
    int categoryId;

    public Product() {}

    public Product(String name, String description, String dietType, double price, int itemId, int prepTime, int categoryId) {
        this.name = name;
        this.description = description;
        this.dietType = dietType;
        this.price = price;
        this.productId = itemId;
        this.prepTime = prepTime;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDietType() {
        return dietType;
    }

    public double getPrice() {
        return price;
    }

    public int getProductId() {
        return productId;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setItemId(int productId) {
        this.productId = productId;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
