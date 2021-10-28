package dev.lytran.model;

public class Product {
    private String productName;
    private int productPrice;
    private int productThumbnail;

    public Product(String productName, int productPrice, int productThumbnail) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productThumbnail = productThumbnail;
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
