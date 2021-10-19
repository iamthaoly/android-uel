package dev.lytran.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Product2 implements Parcelable {
    private int productCode;
    private String  productName;
    private double productPrice;

    public Product2() {

    }

    protected Product2(Parcel in) {
        productCode = in.readInt();
        productName = in.readString();
        productPrice = in.readDouble();
    }

    public static final Creator<Product2> CREATOR = new Creator<Product2>() {
        @Override
        public Product2 createFromParcel(Parcel in) {
            return new Product2(in);
        }

        @Override
        public Product2[] newArray(int size) {
            return new Product2[size];
        }
    };

    @Override
    public String toString() {
        return "Product2:" +
                "productCode=" + productCode +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(productCode);
        parcel.writeString(productName);
        parcel.writeDouble(productPrice);
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
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
}
