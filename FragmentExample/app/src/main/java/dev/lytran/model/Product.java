package dev.lytran.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String productName;
    private int productPrice;
    private int productThumbnail;
    private String productSlogan;

    public Product(String productName, int productPrice, int productThumbnail, String slogan) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productThumbnail = productThumbnail;
        this.productSlogan = slogan;
    }

    public String getProductSlogan() {
        return productSlogan;
    }

    public void setProductSlogan(String productSlogan) {
        this.productSlogan = productSlogan;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(int productThumbnail) {
        this.productThumbnail = productThumbnail;
    }
}
