package com.teamone.salesmanagement.Product;


public class Product {
    byte[] productImage;
    String productCode;
    String productName;
    double productPrice;
    String productSize;

    public Product() {
    }

    public Product(byte[] productImage, String productCode, String productName, double productPrice, String productSize) {
        this.productImage = productImage;
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productSize = productSize;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] imageProduct) {
        this.productImage = imageProduct;
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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }
}