package com.teamone.salesmanagement.Product;

import android.graphics.Bitmap;

public class Product {
    int img;
    String id, name, price, address;

    public Product() {
    }

    public Product(int img, String id, String name, String price, String address) {
        this.img = img;
        this.id = id;
        this.name = name;
        this.price = price;
        this.address = address;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}