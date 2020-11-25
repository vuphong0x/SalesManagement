package com.teamone.salesmanagement.Product;

import android.graphics.Bitmap;

public class Product {
    private Bitmap productImage;
    private String productCode;
    private String productName;
    private Double productPrice;
    private String productSize;

    public Product() {
    }

    public Product(Bitmap productImage, String productCode, String productName, Double productPrice, String productSize) {
        this.productImage = productImage;
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productSize = productSize;
    }

    public Bitmap getProductImage() {
        return productImage;
    }

    public void setProductImage(Bitmap productImage) {
        this.productImage = productImage;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }
}
